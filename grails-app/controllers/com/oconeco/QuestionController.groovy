package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestionController {

    QuestionService questionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static scaffold = Question

//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond questionService.list(params), model:[questionCount: questionService.count()]
//    }
//
//    def show(Long id) {
//        respond questionService.get(id)
//    }
//
//    def create() {
//        respond new Question(params)
//    }
//
//    def save(Question question) {
//        if (question == null) {
//            notFound()
//            return
//        }
//
//        try {
//            questionService.save(question)
//        } catch (ValidationException e) {
//            respond question.errors, view:'create'
//            return
//        }
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'question.text', default: 'Question'), question.id])
//                redirect question
//            }
//            '*' { respond question, [status: CREATED] }
//        }
//    }
//
//    def edit(Long id) {
//        respond questionService.get(id)
//    }
//
//    def update(Question question) {
//        if (question == null) {
//            notFound()
//            return
//        }
//
//        try {
//            questionService.save(question)
//        } catch (ValidationException e) {
//            respond question.errors, view:'edit'
//            return
//        }
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.text', default: 'Question'), question.id])
//                redirect question
//            }
//            '*'{ respond question, [status: OK] }
//        }
//    }
//
//    def delete(Long id) {
//        if (id == null) {
//            notFound()
//            return
//        }
//
//        questionService.delete(id)
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.text', default: 'Question'), id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
//    }
//
//    protected void notFound() {
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.text', default: 'Question'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
//    }
}
