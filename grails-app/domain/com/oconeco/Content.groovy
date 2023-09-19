package com.oconeco

/**
 * Generalized content object (document, webpage, email, etc)
 * Went with 'Content' rather than 'Document' hoping to be more general (i.e. messages), but ack that this is not fully intuitive
 */
class Content {
    String title        //required
    String uri          //required
    /** snippets back from search engine, or summary from ML, or actual 'description' from author/publisher */
    String description

    /** url or file path */
    String path
    /** site or machine name */
    String source
    /** any additional parameters, such as url query params, where relevant (optional) */
    String params


    /** extracted text from source document (typically fetched by ContentService.fetchContent(contentDoc) */
    String bodyText
    Long textSize = 0
    /** html/xml version typically, but perhaps other (markdown, json,...) */
    String structuredContent
    Long structureSize = 0

    String author
    String language

    // todo -- revisit type/subtype most oriented to web results, i.e. Brave api 'type' result value
    //    ThingType type            // todo -- maybe switch from String to more controlled typing??
    String type
    String subtype
    String mimeType

    Date publishDate
    /** originally added to capture Brave api 'family_friendly' flag info... */
    String offensiveFlag = ''
    /** assuming favicon is more intuitive than 'image', but could be any url-ish image for this content */
    String favicon
    Date dateCreated
    Date lastUpdated

    static hasMany = [fragments: ContentFragment, tags: Tag, comments: Comment]

    static constraints = {
        path nullable: true
        source nullable: true
        params nullable: true

        description nullable: true, type: 'text', widget:'textarea'
        bodyText nullable: true, type: 'text'
        structuredContent nullable: true, type: 'text'

        author nullable: true
        language nullable: true

        type nullable: true
        subtype nullable: true
        mimeType nullable: true
        offensiveFlag nullable: true
        favicon nullable: true, size:(0..2048)

        publishDate nullable: true
    }

    @Override
    String toString() {
        return title
    }
}
