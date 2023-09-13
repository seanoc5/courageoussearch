package com.oconeco

class SearchResult {
    /** actual search terms */
    String query
    /** parent search (which might include several templates, and each template can have multiple configurations) */
    Search search
    /** optional search type -- typically set in returned results by source engine (and config) */
//    ThingType type
    String type

    /** list of search result documents */
//    List<Content> documents = []
    /** link back to searchTemplate  */
    SearchTemplate template
    SearchConfiguration config

    List<Tag> tags = []
    List<Comment> comments = []
    List<Content> documents = []


    Date dateCreated
    Date lastUpdated

    static belongsTo = [search: Search]
    static hasMany = [documents: Content, tags: Tag, comments: Comment, analyzers:Analyzer]

    static constraints = {
        type nullable: true
        template nullable: true
        search nullable: true
        template nullable: true
        config nullable: true
        documents widget: 'manyToManyWidget'
    }

    @Override
    String toString() {
        return "${query} [${config}]"
    }

}
