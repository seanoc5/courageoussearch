package com.oconeco

class ContextController {

    ContextService contextService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static scaffold = Context

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond conceptService.list(params), model:[conceptCount: conceptService.count()]
    }


    def autoComplete() {
        List<Context> contexts = Context.findAllByLabelIlike("%${params.term}%")
        log.info "ContextController autocomplete, params: $params, results: ${contexts*.label}"
        respond contexts, model: [contextCount: contextService.count()]
    }


/*


    def show(Long id) {
        respond conceptService.get(id)
    }

    def create() {
        respond new Concept(params)
    }

    def save(Concept concept) {
        if (concept == null) {
            notFound()
            return
        }

        try {
            conceptService.save(concept)
        } catch (ValidationException e) {
            respond concept.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'concept.text', default: 'Concept'), concept.id])
                redirect concept
            }
            '*' { respond concept, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond conceptService.get(id)
    }

    def update(Concept concept) {
        if (concept == null) {
            notFound()
            return
        }

        try {
            conceptService.save(concept)
        } catch (ValidationException e) {
            respond concept.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'concept.text', default: 'Concept'), concept.id])
                redirect concept
            }
            '*'{ respond concept, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        conceptService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'concept.text', default: 'Concept'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.text', default: 'Concept'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
*/
}
