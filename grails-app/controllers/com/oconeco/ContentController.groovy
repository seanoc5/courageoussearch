package com.oconeco

class ContentController {

    ContentService contentService
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
/*

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond contentService.list(params), model:[contentCount: contentService.count()]
    }

    def show(Long id) {
        respond contentService.get(id)
    }

    def create() {
        respond new Content(params)
    }

    def save(Content content) {
        if (content == null) {
            notFound()
            return
        }

        try {
            contentService.save(content)
        } catch (ValidationException e) {
            respond content.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'content.text', default: 'Content'), content.id])
                redirect content
            }
            '*' { respond content, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond contentService.get(id)
    }

    def update(Content content) {
        if (content == null) {
            notFound()
            return
        }

        try {
            contentService.save(content)
        } catch (ValidationException e) {
            respond content.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'content.text', default: 'Content'), content.id])
                redirect content
            }
            '*'{ respond content, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        contentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'content.text', default: 'Content'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.text', default: 'Content'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
*/
}
