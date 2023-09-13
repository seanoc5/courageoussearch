package com.oconeco

import grails.gorm.services.Service
import groovy.json.JsonSlurper
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

interface ISearchService {

    Search get(Serializable id)

    List<Search> list(Map args)

    Long count()

    void delete(Serializable id)

    Search save(Search search)

}


@Service(Search)
abstract class SearchService implements ISearchService {
    DateTimeFormatter braveAgeFormatter = DateTimeFormatter.ofPattern('MMMM d, u')
    SearchResultService searchResultService

    /**
     * simple placeholder to default to 'browse.gsp' display -- todo consider better 'show()' methiod??
     * @param id
     * @return
     */
    Search browse(Serializable id) {
        log.debug "\t\tBrowse($id)"
        Search search = Search.get(id)
        return search
    }

    Search execute(Search search) {
        log.info "\t\texecute search: '${search}' "
        if (search.query.size() > 1) {
            log.debug "basic sanity check on search.query passes..."
        } else if (search.searchTemplates.size() == 0) {
            // todo better checking here, send back visual
            log.warn "No valid search template, bailing out... "
            return search
        } else {
            log.warn "basic sanity check on search.query(${search.query}) fails..."
            return search
        }

        // todo -- fixme...? had trouble getting searchResult documents to save, this save() is a hacked solution...
        search.save()

        search?.searchTemplates?.each { SearchTemplate template ->
            // todo -- what else should a template have? Default HttpClient??  Default Params? Default

            log.info "\t\t\ttemplate: $template"
            template.searchConfigs.each { SearchConfiguration config ->
                log.debug "\t\t\t\tsearchConfiguration: $config"

                def response = config.doSearch(search)
                log.debug "\t\tResponse: $response"
                SearchResult searchResult = buildResult(search, response, config, template)
                if (searchResult.validate()) {
                    def foo = searchResultService.save(searchResult)
                    search.addToSearchResults(searchResult)
                    log.debug "\t\tadded result:$searchResult (config:$config)"
                } else {
                    log.warn "Search result($searchResult) is not valid: ${searchResult.errors}"
                }
            }

            log.debug "\t\tDone with template: $template"
        }

        if (search.validate()) {
            try {
                def bar = search.save()         // todo -- revisit, and see if this is resonable to save() here...?
                log.info "Saved search ($search) with results: ${search.searchResults}"
            } catch (Exception e) {
                log.error "Exception on search.save(): $e"
            }
        } else {
            log.warn "Validation errors: ${search.errors}"
        }
        return search
    }


    /**
     * wrapper to parse string response from brave into json, and then build a SearchResult, along with the web results as Contents
     * @param search - gorm object from UI (not yet persisted)
     * @param response - String from api call to parse into json
     * @return SearchResult with parent search set, ready for persisting
     *
     * todo - make this more general, and handle other types
     * todo - move this to a more portable location/implementation
     */
    SearchResult buildResult(Search search, def response, SearchConfiguration config, SearchTemplate template) {
        JsonSlurper slurper = new JsonSlurper()
        String body = response.body()
        def json = slurper.parseText(body)
        String query = json.query?.original
        String type = json.type
        SearchResult searchResult = new SearchResult(query: query, type: type, search: search, config: config, template: template)
        def webResults = json.web.results
        webResults.each {
            String safeDesc = Jsoup.clean(it.description, Safelist.basic())
            URL url = new URL(it.url)
            String source = simplifyHost(url.host)
            String path = url.path
            String params = url.query
            Content webdoc = new Content(title: it.title, uri: it.url, description: safeDesc, type: it.type, subtype: it.subtype, source: source, params: params, path: path)
            if (it.age) {
                String age = it.age
                if (age.contains('ago')) {
                    log.debug "Relative date... more TODO: $age"
                } else {
                    log.debug "Assuming this is not a relative date (and is parsable): $age"
                    try {
                        // todo - fix me -- https://stackoverflow.com/questions/2219139/how-to-parse-month-full-form-string-using-dateformat-in-java
                        LocalDate localDate = LocalDate.parse(it.age, braveAgeFormatter)
                        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
                        Date date = Date.from(instant)
                        if (date) {
                            webdoc.publishDate = date
                        }

                    } catch (DateTimeParseException e) {
                        log.warn "Age($age) -- Parsing Exception: $e)"
                    }
                }
            }
            if (webdoc.validate()) {
                webdoc.save()
                def foo = searchResult.addToDocuments(webdoc)
                log.debug "added doc: $webdoc"
            } else {
                log.warn "webdoc($webdoc) does not validate. errors: ${webdoc.errors}"
            }
        }
        Content contentTest = Content.first()

        if (searchResult.validate()) {
            log.debug "\t\tSearch result (parent) template: ${searchResult.template} && config: ${searchResult.config}"
/*
            try {
            // todo -- review this, probably should not try to save, since this belongs to Search, and that save will cascade... ???
//                def foo = searchResult.save()
//                log.debug "Saved search result: $searchResult"
            } catch (PropertyValueException pve) {
                log.warn "Problem saving: $pve"
            } catch (Exception e){
                log.warn "Unknown exception: $e"
            }
*/
        } else {
            log.warn "Search result ($searchResult) validation errors: ${searchResult.errors}"
        }
        return searchResult
    }

    /**
     * simple method to strip 'www' (and similar in the future) to get shorter display, and normalize things like www.foo.com and www2.foo.com
     * @param fullHost
     * @return simplified host name
     */
    String simplifyHost(String fullHost){
        String s = null
        if(fullHost.startsWith('www')){
            s = fullHost[4..-1]
            log.debug "\t\tsimplify host($fullHost) to simpler($s)"
        } else {
            s = fullHost
            log.debug "\t\tNothing to simplify in fullHost ($fullHost)?? "
        }
        return s
    }

}


/*
HttpGet httpGet = new HttpGet("https://postman-echo.com/get");
URI uri = new URIBuilder(httpGet.getUri()).addParameter("param1", "value1")
   .addParameter("param2", "value2")
   .build();
httpGet.setUri(uri);

try (CloseableHttpClient client = HttpClients.createDefault();
   CloseableHttpResponse response = (CloseableHttpResponse) client
     .execute(httpGet, new CustomHttpClientResponseHandler())) {

   final int statusCode = response.getCode();
   assertThat(statusCode, equalTo(HttpStatus.SC_OK));
}

//        HttpClient client = HttpClient.create(baseUrl.toURL())
//        HttpRequest request = HttpRequest.GET(UriBuilder.of(url)
//                     .queryParam('limit', 25)
//                     .queryParam('media', 'music')
//                     .queryParam('entity', 'album')
//                     .queryParam('q', "http get request grails 2023")
//                     .build())
//        HttpResponse<String> resp = client.toBlocking().exchange(request, String)
//             String json = resp.body()
        //         ObjectMapper objectMapper = new ObjectMapper()
        //         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        //         SearchResult searchResult = objectMapper.readValue(json, SearchResult)
        //         searchResult.results


//import io.micronaut.http.client.BlockingHttpClient
//import io.micronaut.http.client.HttpClient
//import io.micronaut.http.HttpRequest
//import io.micronaut.http.HttpResponse
//import io.micronaut.http.client.exceptions.HttpClientResponseException
//import io.micronaut.http.uri.UriBuilder
//i

 */
