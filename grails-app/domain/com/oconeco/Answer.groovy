package com.oconeco

class Answer {
    /**  short label for longer text answer */
    String label
    Question question
    /** the optional text answer */
    String description
    Content answerDocument
    User createdBy

    Date dateCreated
    Date lastUpdated

    static belongsTo = [question: Question]

    static hasMany = [tags: Tag, fragments: ContentFragment,
//                      statements: Statement,
                      comments: Comment]

    static constraints = {
        description nullable: true
        answerDocument nullable: true
        createdBy nullable: true
    }

}
