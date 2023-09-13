package com.oconeco

class Question {
    String text         // todo - revisit name, perhaps 'question'?
    Topic topic
    ThingType type
    User createdBy

    Date dateCreated
    Date lastUpdated

    // https://docs.grails.org/latest/ref/Domain%20Classes/hasMany.html
    static hasMany = [searches: Search, answers: Answer, tags: Tag, comments: Comment, contexts:Context]

    static constraints = {
        text nullable: false, type:'text'
        topic nullable: true
        type nullable: true
        createdBy nullable: true
    }

    @Override
    String toString() {
        return "${text}"
    }

}
