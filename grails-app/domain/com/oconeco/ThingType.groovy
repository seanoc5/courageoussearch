package com.oconeco

/**
 * Initial placeholder for defining types of "things"
 * e.g. Concept type, Topic type, Statement type,...
 * todo -- more code here as we learn more
 */
class ThingType {
    String label
    String parentClass      // if parentClass is null, this is a general type, such as 'Place', or 'Time'
    String description

    Date dateCreated
    Date lastUpdated

    static hasMany = [tags: Tag, comments:Comment]

    static constraints = {
        description nullable: true
        parentClass nullable: true
    }

    @Override
    String toString() {
        if(parentClass){
            return "${label}"
        }else {
            return "${label} [$parentClass]"
        }
    }

}
