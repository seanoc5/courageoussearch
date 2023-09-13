package com.oconeco

/**
 * Domain object to wrap and assist with flexible post-search analysis
 * <br>
 * Can be 'run' at the (Search.SearchTemplate.SearchConfiguration.)Content level (i.e. each web doc result), <br>
 * or at the Search.SearchTemplate.SearchConfiguration level <br>
 * or at the Search.SearchTemplate or even just the Search level  (more TODO for higherr levels)
 */
class Analyzer {
    String label
    String description
    /** closure type groovy code to be run) */
    String code
    User createdBy

    Date dateCreated
    Date lastUpdated

    static hasMany = [
//            contexts:Context,
            tags:Tag]
    static constraints = {
        description nullable: true, type: 'text'
        code nullable: true
        createdBy nullable: true
    }


    String toString(){
        return label
    }
}
