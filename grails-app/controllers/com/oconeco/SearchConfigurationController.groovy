package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SearchConfigurationController {

    SearchConfigurationService searchConfigurationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond searchConfigurationService.list(params), model:[searchConfigurationCount: searchConfigurationService.count()]
    }

    def show(Long id) {
        respond searchConfigurationService.get(id)
    }

    def create() {
        respond new SearchConfiguration(params)
    }

    def save(SearchConfiguration searchConfiguration) {
        if (searchConfiguration == null) {
            notFound()
            return
        }

        try {
            searchConfigurationService.save(searchConfiguration)
        } catch (ValidationException e) {
            respond searchConfiguration.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), searchConfiguration.id])
                redirect searchConfiguration
            }
            '*' { respond searchConfiguration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond searchConfigurationService.get(id)
    }

    def update(SearchConfiguration searchConfiguration) {
        if (searchConfiguration == null) {
            notFound()
            return
        }

        try {
            searchConfigurationService.save(searchConfiguration)
        } catch (ValidationException e) {
            respond searchConfiguration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), searchConfiguration.id])
                redirect searchConfiguration
            }
            '*'{ respond searchConfiguration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        searchConfigurationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
