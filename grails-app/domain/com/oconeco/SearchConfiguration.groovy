package com.oconeco

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * specification of a search configuration, essentially mapping to one search engine with (optional) pre-established configurations
 *
 * <br>
 * A search will likely have one or more search templates
 * each search template will have one or more search configurations
 *
 */
class SearchConfiguration {
    /** friendly name for configuration */
    String label
    String description
    boolean defaultConfig = false
    /** url for search engine */
    String url
    User createdBy

    /** string version of paramsJson */
    String paramsJson
    String headersJson

//    List<Concept> concepts = []
//    List<Tag> tags = []
//    List<Comment> comments = []

    Date dateCreated
    Date lastUpdated

    static hasMany = [tags: Tag, comments: Comment, analyzers:Analyzer]

    static constraints = {
        description nullable: true
        url nullable: true
        headersJson nullable: true
        paramsJson nullable: true
        createdBy nullable: true
    }

//    static mapping = {
//        tags fetch: 'join'
//    }

    // todo -- revisit GORM/domain mapping and Java Map (default map objects made sub-domains which felt "icky"...)
    Map<String, String> headers = [:]
    Map<String, Object> params = [:]

    def client
    static transients = ['headers', 'params', 'client']


    @Override
    public String toString() {
        return "$label"
    }

    void setHeaders(Map<String, String> headers) {
        this.headers = headers
        this.headersJson = JsonOutput.toJson(headers)
    }

    /**
     * cowardly avoiding handling Map properties with (g)ORM, just persisting String value...
     * @return
     */
    Map<String, String> getHeaders() {
        if (headers && headers.size()) {
            log.info "Headers already set: $headers (no json converting needed)"
        } else if (headersJson) {
            log.debug "Converting string($headersJson) to map"
            headers = new JsonSlurper().parseText(headersJson)
        } else {
            log.warn "No headers nor headersJson found??? $this"
        }
        return headers
    }


    /**
     * cowardly avoiding handling Map properties with (g)ORM, just persisting String value...
     * @return
     */
    Map<String, String> getParams() {
        if (params) {
            return params
        } else if(paramsJson){
            log.debug "Converting string($req) to map"
            params = new JsonSlurper().parseText(paramsJson)
        } else {
            log.debug "no params, nor paramsJson"
        }
        return params
    }

    void setParams(Map<String, Object> params) {
        this.params = params
        this.paramsJson = JsonOutput.toJson(params)
    }

    // todo - move this to custom helper class (extensible)
    def doSearch(Search search) {
        log.info "\t\tExecute search: $search from config: $label"

        Map<String, Object> cfgPArams = params ?: new JsonSlurper().parseText(paramsJson)
        String q = URLEncoder.encode(search.query, "UTF-8")
        StringBuilder sb = new StringBuilder(url + "?q=$q")
        if(cfgPArams){
            cfgPArams.each { String key, Object val ->
                String p = URLEncoder.encode(val.toString(), 'UTF-8')
                sb.append("&$key=$p")
            }
        }

        String searchUrl = sb.toString()
        log.info "Search url: $searchUrl"

        def builder = HttpRequest.newBuilder()
                .uri(URI.create(searchUrl))
                .GET()
        getHeaders().each { k, v ->
            log.debug "\t\tadd header, Key:$k --> val:$v"
            builder.header(k, v)
        }

        HttpRequest request = builder.build()

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        log.info "URL: $searchUrl -- got response: ${response}"
        return response

    }

}

