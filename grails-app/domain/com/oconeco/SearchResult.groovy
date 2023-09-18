package com.oconeco

class SearchResult {
    /** actual search terms */
    String query
    /** parent search  */
    Search search
    /** optional search type -- typically set in returned results by source engine (and config) */
    String type
    String responseBody
    Integer statusCode

    SearchConfiguration config

    List<Tag> tags = []
    List<Comment> comments = []
    /** list of search result documents */
    List<Content> documents = []


    Date dateCreated
    Date lastUpdated

    static belongsTo = [search: Search]
    static hasMany = [documents: Content, tags: Tag, comments: Comment, analyzers:Analyzer]

    static constraints = {
        type nullable: true
        search nullable: true
        config nullable: true
        statusCode nullable: true
        responseBody type:'text', nullable: true
    }

    @Override
    String toString() {
        return "${query} [${config}]"
    }

}
