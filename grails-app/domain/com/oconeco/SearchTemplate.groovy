package com.oconeco

/**
 * Wrapper to establish what search engines to search and how (what params/settings)
 *  * <br>
 * A search will likely have one or more search templates
 * each search template will have one or more search configurations

 */
class SearchTemplate {
    String label
    String description
    User createdBy

//    Topic topic
    boolean defaultTemplate = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [searchConfigs: SearchConfiguration, contexts:Context,
                      tags: Tag, comments: Comment, analyzers: Analyzer
//                      topics:Topic,
    ]

    // fetch [SearchConfiguration: "join"]      // https://www.youtube.com/watch?v=6pIOgv7cZzo&t=2s ~minute 5
    static constraints = {
        description nullable: true
//        topic nullable: true
        createdBy nullable: true
    }

    @Override
    String toString() {
        return "${label} (configs: ${searchConfigs})"
    }

}
