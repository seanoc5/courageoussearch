package com.oconeco

class Comment {
    String label
    String description
    User user
    String url

    Date dateCreated
    Date lastUpdated

    static constraints = {
        description nullable: true
        user nullable: true
        url nullable: true
    }
}
