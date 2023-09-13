package com.oconeco

class Organization {
    String name
    String url
    String description

    Date dateCreated
    Date lastUpdated

    static hasMany = [people: User, contexts:Context, tags:Tag, comments:Comment]
    static constraints = {
        url nullable: true
        description nullable: true, type: 'text'
    }

    @Override
    String toString() {
        return "${name}"
    }

}
