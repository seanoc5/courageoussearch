package com.oconeco

class User {
    String username
    String firstName
    String lastName
    String fullName
    String contactInformation
    Boolean human = true

    static constraints = {
        fullName nullable: true
        contactInformation nullable: true
    }

    @Override
    String toString() {
        return "${username}"
    }

}
