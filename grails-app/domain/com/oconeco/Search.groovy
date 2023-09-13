package com.oconeco

/**
 * Typically a createdBy-initiated search
 */
class Search {
    String query

    String additionalParams     //    Map<String, String> additionalParams
    User createdBy
    Context context
    ThingType type

    Date dateCreated
    Date lastUpdated

    static hasMany = [
            searchResults: SearchResult, searchTemplates: SearchTemplate,
            topics       : Topic,
//            concepts: Concept,
            subjects: Subject,
            tags         : Tag, comments: Comment
    ]

    static constraints = {
        additionalParams nullable: true, type: 'text'

        createdBy nullable: true
        context nullable: true
        type nullable: true
    }

    static mapping = {
        searchResults lazy: false
    }

    @Override
    String toString() {
        return "${query}"
    }
}
