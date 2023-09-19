package com.oconeco

class   ContentController {
    ContentService contentService
    FetchService fetchService

    static scaffold = Content

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def listBySource(def params){
        log.info "Content listBySource($params)"
        String source = ''
        if(params.id){
            Long id = ((String)params.id).toLong()
            Content c = Content.get(id)
            source = c.source
            log.info "found id($id) in params, getting source($source) from content/doc:${c}"
        } else if(params.source) {
            source = params.source
            log.info "found source($source) in params, using that string..."
        } else {
            source =  'cnn'
            log.warn "setting debug/default value to hardcoded: $source"
        }
        String s = "%${source}%"
        log.info "Using source ilike: $s"
        if(!params.max){
            params.max = 10
            log.info "setting params.max (not found) to default ${params.max}"
        }           // [max: 10, sort: "title", order: "desc", offset: 100]
        List<Content> docs = Content.findAllBySourceIlike(s, params)
        respond docs, model:[contentCount: contentService.count()], view: 'index'
    }

    /**
     * entry point to get details and then call something (ContentService for now) to get the page text
     * this will likely need to check if the URL is a single-page-arch (SPA) and if so, fire up GEB-ish stuff to get headless browser to get the resolved text
     *
     * todo add Organization awareness to better know how to get text, and how to strip boilerplate wrapping (header/nav/footer)...
     *
     * @param id Content/doc id to go fetch
     *
     */
    def fetchContent(Long id) {
        log.info "fetchContent(id:$id)..."
        Content doc = Content.get(id)
        if (doc) {
            fetchService.fetchContent(doc)
//        render text: "More code here...."
        } else {
            log.warn "Get flash message here about what went wrong... couldn't find..."
        }
        redirect action: 'show', id: id
    }

}
