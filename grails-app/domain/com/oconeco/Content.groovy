package com.oconeco

/**
 * Generalized content object (document, webpage, email, etc)
 */
class Content {
    String title        //required
    String uri          //required

    /** url or file path */
    String path
    /** site or machine name */
    String source
    /** any additional parameters, such as url query params, where relevant (optional) */
    String params

    String content
    String description
    /** html/xml version typically, but perhaps other (markdown, json,...) */
    String structuredContent
    // todo - move this to more explicit tracking and identification, machine learning, etc */
    String author
    String language

    // todo -- revisit type/subtype
    /** most oriented to web results, i.e. Brave api 'type' result value */
//    ThingType type
    String type
    String subtype
    Date publishDate

//    List<Tag> tags = []
//    List<Comment> comments = []
//    List<ContentFragment> fragments = []

    Date dateCreated
    Date lastUpdated

    static hasMany = [fragments: ContentFragment, tags: Tag, comments: Comment]

    static constraints = {
        path nullable: true
        source nullable: true
        params nullable: true

        description nullable: true, type: 'text', widget:'textarea'
        content nullable: true, type: 'text'
        structuredContent nullable: true, type: 'text'

        author nullable: true
        language nullable: true

        type nullable: true
        subtype nullable: true

        publishDate nullable: true
    }

    @Override
    String toString() {
//        return "${title} ($uri)"
        return title
    }
}
