package com.oconeco

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import java.net.http.HttpResponse

import static org.springframework.http.HttpStatus.*

class SearchController {

    SearchService searchService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static scaffold = Search


    def save(Search search) {
        log.info "Params: $params"
        if (search == null || !(search.query?.size() > 1)) {
            notFound()
            return
        }

        SearchResult searchResult = searchService.execute(search)
        log.info "Search result: $searchResult"

        session.lastSearch = search

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'search.text', default: 'Search'), search.id])
                redirect search
            }
            '*' { respond search, [status: CREATED] }
        }
    }

    def browse(Long id) {
        log.info "Lasts Search: ${session.lastSearch} from session...?"
        def search = searchService.browse(id)
        respond search
    }
//
//    def edit(Long id) {
//        respond searchService.get(id)
//    }
//
//    def update(Search search) {
//        if (search == null) {
//            notFound()
//            return
//        }
//
//        try {
//            searchService.save(search)
//        } catch (ValidationException e) {
//            respond search.errors, view:'edit'
//            return
//        }
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'search.text', default: 'Search'), search.id])
//                redirect search
//            }
//            '*'{ respond search, [status: OK] }
//        }
//    }
//
//    def delete(Long id) {
//        if (id == null) {
//            notFound()
//            return
//        }
//
//        searchService.delete(id)
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'search.text', default: 'Search'), id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
//    }
//
//    protected void notFound() {
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'search.text', default: 'Search'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
//    }
}
