package com.oconeco

class SearchTemplateController {

    SearchTemplateService searchTemplateService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static scaffold = SearchTemplate

    def autoComplete() {
        List<SearchTemplate> templates = SearchTemplate.findAllByLabelIlike("%${params.term}%")
        log.info "SearchTemplateController autocomplete, params: $params, results: ${templates*.label}"
        respond templates, model: [templateCount: searchTemplateService.count()]
    }



/*
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond searchTemplateService.list(params), model:[searchTemplateCount: searchTemplateService.count()]
    }

    def show(Long id) {
        respond searchTemplateService.get(id)
    }

    def create() {
        respond new SearchTemplate(params)
    }

    def save(SearchTemplate searchTemplate) {
        if (searchTemplate == null) {
            notFound()
            return
        }

        try {
            searchTemplateService.save(searchTemplate)
        } catch (ValidationException e) {
            respond searchTemplate.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchTemplate.text', default: 'SearchTemplate'), searchTemplate.id])
                redirect searchTemplate
            }
            '*' { respond searchTemplate, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond searchTemplateService.get(id)
    }

    def update(SearchTemplate searchTemplate) {
        if (searchTemplate == null) {
            notFound()
            return
        }

        try {
            searchTemplateService.save(searchTemplate)
        } catch (ValidationException e) {
            respond searchTemplate.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'searchTemplate.text', default: 'SearchTemplate'), searchTemplate.id])
                redirect searchTemplate
            }
            '*'{ respond searchTemplate, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        searchTemplateService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'searchTemplate.text', default: 'SearchTemplate'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchTemplate.text', default: 'SearchTemplate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
*/
}
