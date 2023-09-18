package com.oconeco

class Tag {
    String label
    String description
    User createdBy
    Boolean defaultTag = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [
            comments:Comment
    ]

    static constraints = {
        description nullable: true
        createdBy nullable: true
    }

    @Override
    String toString() {
        return label

    }

}
