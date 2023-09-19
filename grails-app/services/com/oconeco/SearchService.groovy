package com.oconeco

import grails.async.Promise
import grails.gorm.services.Service
import groovy.json.JsonSlurper
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist

import java.net.http.HttpResponse
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
    FetchService fetchService


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

    SearchResult execute(Search search) {
        log.info "\t\texecute search: '${search}'"
        if (search.query.size() > 1) {
            log.debug "basic sanity check on search.query passes..."
        } else {
            log.warn "basic sanity check on search.query(${search.query}) fails..."
            return search
        }

//        // todo -- fixme...? had trouble getting searchResult documents to save, this save() is a hacked solution...
        search.save()

        SearchResult searchResult = null
        SearchConfiguration config = null
        if (search.configuration) {
            config = search.configuration
        } else {
            config = SearchConfiguration.findByDefaultConfig(true)
            search.configuration = config
        }

        HttpResponse<String> response = config.doSearch(search)
        log.debug "\t\tResponse: $response"
        if (response) {
            if (response.statusCode() == 200) {
                searchResult = buildResult(search, response, config)
                if (searchResult.validate()) {
                    def foo = searchResultService.save(searchResult)
                    search.addToSearchResults(searchResult)
                    log.debug "\t\tadded result:$searchResult (config:$config)"
                } else {
                    log.warn "Search result($searchResult) is not valid: ${searchResult.errors}"
                }


                if (search.validate()) {
                    try {
                        def bar = search.save()         // todo -- revisit, and see if this is resonable to save() here...?
                        log.debug "Saved search ($search) with results: ${search.searchResults}... now try fetching content..."

                        log.info "\t\tfetch content for (${searchResult.documents.size()}) docs async (promises)..."
                        Promise<List<String>> foo = fetchService.fetchPromised(searchResult)
                        log.info "what to do with my promises? $foo"

                    } catch (Exception e) {
                        log.error "Exception on search.save(): $e"
                    }
                } else {
                    log.warn "Validation errors: ${search.errors}"
                }
            } else {
                String rspStr = response.body()
                int scode = response.statusCode()
                searchResult = new SearchResult(query: search.query, search: search, response: rspStr, config: config, type: "Error: ${scode}", statusCode: scode)
                        .save()
                search.addToSearchResults(searchResult)
                String msg = "Did not get the expected status code of 200, we got:(${response.statusCode()}) for search ($search) -- response: $response"
                log.warn msg
                if (search.validate()) {
                    search.save()       // todo -- show error message, add link to search config if it is missing the right token (brave search?)
                } else {
                    log.warn "Error(s) saving search:($search) -- ${search.errors}"
                }
            }
        } else {
            log.warn "NO response from search($search)... something out of sync??"
        }
        return searchResult
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
    SearchResult buildResult(Search search, HttpResponse response, SearchConfiguration config) {
        JsonSlurper slurper = new JsonSlurper()
        String body = response.body()
        def json = slurper.parseText(body)
        String query = json.query?.original
        String type = json.type
        int scode = response.statusCode()
        log.info "Search status: ${search.isDirty()}"
        SearchResult searchResult = new SearchResult(query: query, type: type, search: search, config: config, responseBody: body, statusCode: scode)

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

        if (searchResult.validate()) {
            // leaving save of this searchResult to 'parent' search.save() (from caller???)
            log.debug "\t\tSearch result (parent) Search: ${search} && config: ${searchResult.config}"
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
    String simplifyHost(String fullHost) {
        String s = null
        if (fullHost.startsWith('www')) {
            s = fullHost[4..-1]
            log.debug "\t\tsimplify host($fullHost) to simpler($s)"
        } else {
            s = fullHost
            log.debug "\t\tNothing to simplify in fullHost ($fullHost)?? "
        }
        return s
    }


}

