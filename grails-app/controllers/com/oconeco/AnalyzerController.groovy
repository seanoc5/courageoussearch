package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnalyzerController {

    AnalyzerService analyzerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond analyzerService.list(params), model:[analyzerCount: analyzerService.count()]
    }

    def show(Long id) {
        respond analyzerService.get(id)
    }

    def create() {
        respond new Analyzer(params)
    }

    def save(Analyzer analyzer) {
        if (analyzer == null) {
            notFound()
            return
        }

        try {
            analyzerService.save(analyzer)
        } catch (ValidationException e) {
            respond analyzer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'analyzer.text', default: 'Analyzer'), analyzer.id])
                redirect analyzer
            }
            '*' { respond analyzer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond analyzerService.get(id)
    }

    def update(Analyzer analyzer) {
        if (analyzer == null) {
            notFound()
            return
        }

        try {
            analyzerService.save(analyzer)
        } catch (ValidationException e) {
            respond analyzer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'analyzer.text', default: 'Analyzer'), analyzer.id])
                redirect analyzer
            }
            '*'{ respond analyzer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        analyzerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'analyzer.text', default: 'Analyzer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'analyzer.text', default: 'Analyzer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
