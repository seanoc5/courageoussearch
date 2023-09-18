package com.oconeco

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TagController {

    TagService tagService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Transactional
    def createOrUpdate() {
        def requestData = request.JSON // Extract JSON data from the request
        def tag = new Tag(requestData) // Create a new domain instance from JSON
        if (tag.save(flush: true)) {
            log.info "Created new tag: $tag from request data:$requestData"
            render status: 201, text: "Created or updated the domain object with id ${tag.id}"
        } else {
            render status: 400, text: "Failed to create or update the domain object"
        }
        /*
        In this example:

        We create a custom action called createOrUpdate() in the MyController class.

        Within the createOrUpdate() action, we extract the JSON data from the request using request.JSON.

        We create a new instance of your domain class (MyDomain) using the JSON data received in the request.

        We attempt to save the domain instance using save(). If it's successful, we respond with a status code of 201 (Created),
        indicating success. Otherwise, we respond with a status code of 400 (Bad Request).

        This custom action allows you to handle JSON data for creating or updating domain objects.
        You can send JSON data to this action using a POST request with the appropriate headers, such as Content-Type: application/json.
         Make sure to adapt this code to your specific domain class and requirements.

         */
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tagService.list(params), model: [tagCount: tagService.count()]
    }

    def show(Long id) {
        respond tagService.get(id)
    }

    def create() {
        respond new Tag(params)
    }

    def save(Tag tag) {
        log.info "Save tag from params: $params"

        if (tag == null) {
            notFound()
            return
        }


//        List<Long> contextIds = params.list('contentContexts')?.collect { String it ->
//            it.toLong()
//        }
//        log.info "Save($tag) contextIds($contextIds)"
//        contextIds.each {
//            Context context = Context.get(it)
//            if(context) {
//                log.info "adding context($context) to tag($tag)"
//                tag.addToContexts(context)
//            } else {
//                log.warn "Context ID($it) does not exist somehow?? (TagController.save($tag))"
//            }
//        }


        try {
            tagService.save(tag)
            log.info "Saved Tag: $tag"
        } catch (ValidationException e) {
            respond tag.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tag.text', default: 'Tag'), tag.id])
                redirect tag
            }
            '*' { respond tag, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tagService.get(id)
    }

    def update(Tag tag) {
        if (tag == null) {
            notFound()
            return
        }

        try {
            tagService.save(tag)
        } catch (ValidationException e) {
            respond tag.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tag.text', default: 'Tag'), tag.id])
                redirect tag
            }
            '*' { respond tag, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tagService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tag.text', default: 'Tag'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.text', default: 'Tag'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
