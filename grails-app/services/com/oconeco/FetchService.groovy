package com.oconeco

//https://async.grails.org/latest/guide/index.html#introduction
import static grails.async.Promises.*

import grails.async.Promise
import grails.async.PromiseList
import grails.gorm.transactions.Transactional
import groovy.transform.CompileDynamic
import net.dankito.readability4j.Article
import net.dankito.readability4j.Readability4J
import org.apache.tika.config.TikaConfig
import org.apache.tika.exception.TikaException
import org.apache.tika.io.TikaInputStream
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.sax.TeeContentHandler
import org.apache.tika.sax.ToXMLContentHandler
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Safelist
import org.xml.sax.SAXException

@Transactional
class FetchService {
    ContentService contentService
    TikaConfig tikaConfig = new TikaConfig()
    public static final String UNKNOWN = 'unknown'


    /**
     * placeholder method for reaching out to fetch remote content
     * todo -- switch to Micronaut client?, check pretty much everything, this is a very naive implementation...
     * @param resultDoc
     * @return Content/Doc with html added to structuredContent, and parsed text in text field (if possible)
     */
//    @Transactional
    Content fetchContent(Content resultDoc) {
        if (resultDoc) {
            String url = resultDoc.uri          // Define the URL you want to parse
            log.info "\t\tfetchContent(url: $url)..."
            URL u = new URL(resultDoc.uri)

            try (TikaInputStream tikaInputStream = TikaInputStream.get(u)) {
                Metadata metadata = new Metadata()

                //TikaInputStream sets the TikaCoreProperties.RESOURCE_NAME_KEY when initialized with a file or path
                String mimetype = tikaConfig.getDetector().detect(tikaInputStream, metadata)
                if (mimetype) {
                    log.debug "\t\tresult doc mimetype($mimetype) detected by tika in fetchContent()..."
                } else {
                    log.warn "Tika detected NO VALID mimetype ($mimetype) found for resultdoc($resultDoc), setting to '$UNKNOWN' and continuing to throw it at tika to parse (bad idea??)... "
                    mimetype = UNKNOWN
                }
                if (mimetype.containsIgnoreCase('html')) {
                    log.info "use html approach (Readability4j)??"
                    extractReadabilityBodyText(resultDoc, tikaInputStream)
//                    extractHtmlBodyText(resultDoc)
                } else {
                    log.info "mimetype: $mimetype"
                    extractTikaBodyText(resultDoc, tikaInputStream)
                }
                String bodyText = resultDoc.bodyText

                if (bodyText) {
                    // todo improve check method on bodyText confidence....
                    Float confidence = contentService.bodyTextLooksReasonableConfidence(bodyText, resultDoc)
                    if (confidence >= contentService.bodyTextConfidenceMinRatio) {
                        log.debug "\t\tconfidence: $confidence"
                    } else {
                        log.warn "Confidence is low ($confidence) that this page is complete (i.e. likely SPA shell): $resultDoc"
                        // todo -- add logic to switch to GEB/webdriver to get 'full' page
                    }

                    // todo -- move this to contentService?? async transactions... any pro/con...?
                    if (resultDoc.validate()) {
                        resultDoc.save()
                    } else {
                        log.warn "Content/Doc has validation errors during 'fetchContent($resultDoc): ${resultDoc.errors}"
                    }
                } else {
                    log.warn "No body found!!? Url: $url -- mimetype:$mimetype"
                }
            } catch (IOException ioException) {
                log.warn "IO Exception: $ioException -- doc:$resultDoc"
            } catch (SAXException e) {
                log.warn "SAX Exception: $e -- doc:$resultDoc"
            } catch (TikaException e) {
                log.warn "Tika Exception: $e -- doc:$resultDoc"
            }

        } else {
            log.warn "No valid doc($resultDoc) found to fetchContent() for, returning empty-handed...?"
        }
        return resultDoc
    }


    /**
     * Use async pkg and promises/tasks to make fetching web content parallel  (first pass, likely room for improvement)
     * @param searchResult
     * @return
     */
    @CompileDynamic
    Promise<List<String>> fetchPromised(SearchResult searchResult) {
        List<Content> resultDocs = searchResult.documents
        log.info "Fetch with promises (${resultDocs.size()}) docs..."
        PromiseList<String> list = new PromiseList<String>()
        int i = 0
        resultDocs.each { Content doc ->
            i++
            log.info "$i) fetch doc: $doc"
            list << task {
                def foo = fetchContent(doc)
            }
        }
        log.info "return from promise land..."
        return list
    }


    /**
     * Parse the (assumed)html and get just the text back
     * @param resultDoc
     * @return (updated) Content resultDoc
     */
    Content extractHtmlBodyText(Content resultDoc) {
        log.info "TODO: remove boilerplate text for resultDoc($resultDoc)..."
        // https://stackoverflow.com/questions/10518972/open-a-connection-with-jsoup-get-status-code-and-parse-document
        Connection.Response resp = Jsoup.connect(resultDoc.uri)
//                .userAgent(USER_AGENT)
                .timeout(3000)
                .followRedirects(true)
                .execute();

        // save original(ish?) text of HTML doc
        resultDoc.structuredContent = resp.body()

        // start to save extracted text
        Document htmlJsoup = resp.parse()
        Document.OutputSettings settings = new Document.OutputSettings()
        settings.prettyPrint(false)
        try {
            String s = Jsoup.clean(resultDoc.structuredContent, "", Safelist.none(), settings)
            String bodyText =
                    log.info "(lengths s:${s.length()}) -- Str len:${str.length()}"

        } catch (Exception e) {
            log.error "Parsing exception: $e"
        }
        return resultDoc
    }


    /**
     * get content (via tika)
     * @param resultDoc
     * @return
     */
    Content extractTikaBodyText(Content resultDoc, TikaInputStream tikaInputStream) throws IOException, SAXException, TikaException {
        // TODO: remove boilerplate text for resultDoc($resultDoc)...

        ToXMLContentHandler structureHandler = new ToXMLContentHandler()
        BodyContentHandler textHandler = new BodyContentHandler(MAX_BODY_CHARS)
        TeeContentHandler teeContentHandler = new TeeContentHandler(structureHandler, textHandler)

        ParseContext context = new ParseContext()

        AutoDetectParser parser = new AutoDetectParser()
        parser.parse(tikaInputStream, teeContentHandler, metadata, context)

        String body = textHandler.toString()
        resultDoc.bodyText = reformatBodyText(body)
        resultDoc.textSize = resultDoc.bodyText?.size()

        String structured = structureHandler.toString()
        String restructured = reformatStructuredText(structured)
        resultDoc.structuredContent = restructured
        resultDoc.structureSize = restructured.size()


        return resultDoc
    }


    Content extractReadabilityBodyText(Content resultDoc, TikaInputStream tikaInputStream) throws IOException, SAXException, TikaException {
        String html = tikaInputStream.getText()

        Readability4J readability4J = new Readability4J(resultDoc.uri, html); // url is just needed to resolve relative urls
        Article article = readability4J.parse();

        // returns extracted content in a <div> element
        String extractedContentHtml = article.getContent();
        // to get content wrapped in <html> tags and encoding set to UTF-8, see chapter 'Output encoding'
        String extractedContentHtmlWithUtf8Encoding = article.getContentWithUtf8Encoding();
        String extractedContentPlainText = article.getTextContent();
        String title = article.getTitle();
        String byline = article.getByline();            // not working...?
        String excerpt = article.getExcerpt();

        resultDoc.structuredContent = extractedContentHtml
        resultDoc.bodyText = extractedContentPlainText
        resultDoc.textSize = resultDoc.bodyText?.size()
        resultDoc.structureSize = resultDoc.structuredContent?.size()

        return resultDoc

    }
}
