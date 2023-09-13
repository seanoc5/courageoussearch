package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SearchResultController {

    SearchResultService searchResultService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond searchResultService.list(params), model:[searchResultCount: searchResultService.count()]
    }

    def show(Long id) {
        log.debug "Show result id: $id"
        respond searchResultService.get(id)
    }

    def create() {
        respond new SearchResult(params)
    }

    def save(SearchResult searchResult) {
        if (searchResult == null) {
            notFound()
            return
        }

        try {
            if(searchResult.validate()) {
                searchResultService.save(searchResult)
            } else {
                log.warn "SearchResult validation errors: ${searchResult.errors}"
            }
        } catch (ValidationException e) {
            respond searchResult.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchResult.text', default: 'SearchResult'), searchResult.id])
                redirect searchResult
            }
            '*' { respond searchResult, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond searchResultService.get(id)
    }

    def update(SearchResult searchResult) {
        if (searchResult == null) {
            notFound()
            return
        }

        try {
            searchResultService.save(searchResult)
        } catch (ValidationException e) {
            respond searchResult.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'searchResult.text', default: 'SearchResult'), searchResult.id])
                redirect searchResult
            }
            '*'{ respond searchResult, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        searchResultService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'searchResult.text', default: 'SearchResult'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchResult.text', default: 'SearchResult'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
