package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ThingTypeController {

    ThingTypeService thingTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond thingTypeService.list(params), model:[thingTypeCount: thingTypeService.count()]
    }

    def show(Long id) {
        respond thingTypeService.get(id)
    }

    def create() {
        respond new ThingType(params)
    }

    def save(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
        } catch (ValidationException e) {
            respond thingType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
                redirect thingType
            }
            '*' { respond thingType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond thingTypeService.get(id)
    }

    def update(ThingType thingType) {
        if (thingType == null) {
            notFound()
            return
        }

        try {
            thingTypeService.save(thingType)
        } catch (ValidationException e) {
            respond thingType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'thingType.label', default: 'ThingType'), thingType.id])
                redirect thingType
            }
            '*'{ respond thingType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        thingTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'thingType.label', default: 'ThingType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'thingType.label', default: 'ThingType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
