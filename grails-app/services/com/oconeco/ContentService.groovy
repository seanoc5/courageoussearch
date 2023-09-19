package com.oconeco

import grails.gorm.services.Service
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Safelist

interface IContentService {

    Content get(Serializable id)

    List<Content> list(Map args)

    Long count()

    void delete(Serializable id)

    Content save(Content content)
}

@Service(Content)
abstract class ContentService implements IContentService {

    /** quick hack to help establish if the parsed body text looks complete, or just an unfinished ajax/spa shell */
    Float bodyTextConfidenceMinRatio = 0.7f
    public static final int MAX_BODY_CHARS = 10 * 1024 * 1024

//    def springSecurityService


    /**
     * placeholder method to check that the retrieved page has 'reasonable' content (not ajax/spa shell...
     * @param content
     * @param searchResultDoc
     * @return boolean of whether this bodyText looks like it contains the highlighted search result text
     */
    Float bodyTextLooksReasonableConfidence(String content, Content searchResultDoc) {
        List<String> parts = searchResultDoc.description?.split(' ')
        List foundPartsInBody = []
        parts.each { String part ->
            if (content.contains(part)) {
                foundPartsInBody << part
                log.debug "found this 'word'($part) exists in body/text of retrieved page..."
            } else {
                log.debug "\t\tthis 'word' ($part) does NOT EXIST in body/text of retrieved page ($searchResultDoc)"
            }
        }
        Float ratio = foundPartsInBody.size() / parts.size()
        if (ratio > bodyTextConfidenceMinRatio) {
            log.info "Found ratio of ($ratio) found parts which should be greater than $bodyTextConfidenceMinRatio minimum, seems 'reasonable'...? $searchResultDoc"
        } else {
            log.warn "Found ratio of ($ratio) found parts(${foundPartsInBody}) which is not greater than $bodyTextConfidenceMinRatio minimum, seems like this is missing actual content...? $searchResultDoc"
            // todo add tag in place of warning
        }

        // todo add more logic to this method
        return ratio
    }



    /**
     * naive approach to compress newlines. If we get three or more in a row, change to just 2 (which gives paragraph-ish-ness)
     * @param body
     * @return reformmatted body
     */
    String reformatBodyText(String body) {
        if (body) {
            List<String> lines = []
            int blanksConsecutive = 0
            int count = 0
            body.eachLine {
                count++
                String t = it.trim()
                boolean blank = t.size() == 0
                if (blank) {
                    blanksConsecutive++
                    if (blanksConsecutive > 1) {
                        log.debug "\t\t$count) skipping blank line"
                    } else {
                        lines << it
                    }
                } else {
                    blanksConsecutive = 0
                    lines << it
                }
            }
            String reformmated = lines.join('\n').trim()        // trim() should remove leading and trailing blank lines, but leave those included "inside" the join()

//            String reformmated = body.replaceAll('(^\s$){3,}', '\\\\n\\\\n',)
            log.info "Body size: ${body.size()}, reformat size:${reformmated.size()}"
            return reformmated
        } else {
            log.warn "no valid body sent in, skipping reformatBodyText($body)..."
            return ''
        }
    }

    /**
     * naive approach to compress newlines. If we get three or more in a row, change to just 2 (which gives paragraph-ish-ness)
     * @param structuredText
     * @return reformmatted body
     */
    String reformatStructuredText(String structuredText) {
        if (structuredText) {
            List<String> lines = []
            int blanksConsecutive = 0
            int count = 0
            structuredText.eachLine {
                count++
                String t = it.trim()
                boolean blank = t.size() == 0
                if (blank) {
                    // note: remove all blank lines, as opposed to bodytext where we want a blank line to suggest paragraph marks
                    blanksConsecutive++
                    log.debug "\t\t$count) skipping blank line"
                } else {
                    blanksConsecutive = 0
                    lines << it
                }
            }
            String reformmated = lines.join('\n')

            log.info "Structured size: ${structuredText.size()}, reformat size:${reformmated.size()}"
            return reformmated
        } else {
            log.warn "no valid body sent in, skipping reformatBodyText($structuredText)..."
            return ''
        }
    }

}
