package com.oconeco

/**
 *
 */
class Context {
    String label
    String description
    String level
    // todo Move these to more rigorous domain objects
    String time
    String location
    String intent

    boolean defaultContext = false

    User createdBy

    Date dateCreated
    Date lastUpdated

    static hasMany = [
            analyzers: Analyzer,
            tags: Tag,
            examples: ContentFragment,
            comments: Comment]

    static constraints = {
        description nullable: true
        level nullable: true
        createdBy nullable: true
        time nullable: true
        location nullable: true
        intent nullable: true
    }

    @Override
    String toString() {
        return "${label}"
    }

}
