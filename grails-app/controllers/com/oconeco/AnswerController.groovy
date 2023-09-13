package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnswerController {

    AnswerService answerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond answerService.list(params), model:[answerCount: answerService.count()]
    }

    def show(Long id) {
        respond answerService.get(id)
    }

    def create() {
        respond new Answer(params)
    }

    def save(Answer answer) {
        if (answer == null) {
            notFound()
            return
        }

        try {
            answerService.save(answer)
        } catch (ValidationException e) {
            respond answer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answer.text', default: 'Answer'), answer.id])
                redirect answer
            }
            '*' { respond answer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond answerService.get(id)
    }

    def update(Answer answer) {
        if (answer == null) {
            notFound()
            return
        }

        try {
            answerService.save(answer)
        } catch (ValidationException e) {
            respond answer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.text', default: 'Answer'), answer.id])
                redirect answer
            }
            '*'{ respond answer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        answerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.text', default: 'Answer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.text', default: 'Answer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
