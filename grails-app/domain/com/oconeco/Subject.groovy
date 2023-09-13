package com.oconeco

class Subject {
    String label
    String description
    /** level of Structure hierarchy depth */
    Integer level = 0

    User createdBy
    Organization creatorOrg

    Date dateCreated
    Date lastUpdated

    // todo -- review: note: subjects do not have 'Context', only when you get to topics or concepts do we include context
    static hasMany = [subSubjects:Subject, topics:Topic,
//                      concepts:Concept,
                      tags:Tag, examples:ContentFragment, comments:Comment]

    static constraints = {
        description nullable: true, type: 'text'
        createdBy nullable: true
        creatorOrg nullable: true
    }

/*
    static mapping = {
        topics lazy:false
        tags lazy:false
    }
*/


    @Override
    public String toString() {
        return "${label}"
    }
}

