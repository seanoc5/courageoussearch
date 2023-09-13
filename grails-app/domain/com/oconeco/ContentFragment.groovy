package com.oconeco

class ContentFragment {
    String label
    String text
    String description
    String structuredContent
//    String uri
//    String path
    long startPos
    long endPos
    long startTermNum
    long endTermNum
    ThingType type
    String subtype
    String language

    Date dateCreated
    Date lastUpdated

    static belongsTo = [document: Content]
    static hasMany = [
//            concepts:Concept,
            topics:Topic,
//            vocaulary:Vocabulary
    ]
    static constraints = {
        text nullable: true, type:'text'
        description  nullable: true, type: 'text'
        structuredContent nullable: true, type: 'text'
        startPos  nullable: true
        startTermNum nullable: true
        endPos nullable: true
        endTermNum nullable: true
        type nullable: true
        subtype nullable: true
        language nullable: true
    }

    @Override
    String toString() {
        return "${label}"
    }

}
