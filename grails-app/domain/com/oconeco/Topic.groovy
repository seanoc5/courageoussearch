package com.oconeco

class Topic {
    String label
    String description
    boolean defaultTopic
    User createdBy
    /** level of Structure hierarchy depth */
    Integer level = 0

    Date dateCreated
    Date lastUpdated

    static hasMany = [comments: Comment,
//                      concepts: Concept,
//                      contexts:Context,
                      examples: ContentFragment, tags: Tag, questions: Question ]

    static constraints = {
        description nullable: true
        defaultTopic nullable: true
        createdBy nullable: true
    }

    String toString() {
        return "${label}"
    }
}
