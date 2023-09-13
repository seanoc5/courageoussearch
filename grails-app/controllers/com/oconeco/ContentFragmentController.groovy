package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ContentFragmentController {

    ContentFragmentService contentFragmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond contentFragmentService.list(params), model:[contentFragmentCount: contentFragmentService.count()]
    }

    def show(Long id) {
        respond contentFragmentService.get(id)
    }

    def create() {
        respond new ContentFragment(params)
    }

    def save(ContentFragment contentFragment) {
        if (contentFragment == null) {
            notFound()
            return
        }

        try {
            contentFragmentService.save(contentFragment)
        } catch (ValidationException e) {
            respond contentFragment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contentFragment.text', default: 'ContentFragment'), contentFragment.id])
                redirect contentFragment
            }
            '*' { respond contentFragment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond contentFragmentService.get(id)
    }

    def update(ContentFragment contentFragment) {
        if (contentFragment == null) {
            notFound()
            return
        }

        try {
            contentFragmentService.save(contentFragment)
        } catch (ValidationException e) {
            respond contentFragment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contentFragment.text', default: 'ContentFragment'), contentFragment.id])
                redirect contentFragment
            }
            '*'{ respond contentFragment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        contentFragmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contentFragment.text', default: 'ContentFragment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contentFragment.text', default: 'ContentFragment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
