package com.oconeco

import grails.validation.ValidationException
import org.springframework.web.servlet.ModelAndView

import static org.springframework.http.HttpStatus.*

class SubjectController {

    SubjectService subjectService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def find(String label) {
        log.info "params: $params"
        // todo -- add error checking and flash message (see below for example?)
//        if(text==null){
//            text = 'Politics'
//        }
        // todo -- set eager/join fetching to avoid n+1 issues.. based on hibernate debugging/logging, this is not the case
        Subject subject =  subjectService.find(label )

        def mav = new ModelAndView('show', [subject:subject])
//        render(model: [subject: subject], )
        return mav
    }

    def setup(){
        def results = subjectService.dbSetup()
        log.info "Subject Controller dbSetup() returned :$results"
//        respond view: "show", model:s
        redirect(action: "index")
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def foo = subjectService.list(params)
        respond foo, model:[subjectCount: subjectService.count()]
    }

    def show(Long id) {
        respond subjectService.get(id)
    }

    def create() {
        respond new Subject(params)
    }

    def save(Subject subject) {
        if (subject == null) {
            notFound()
            return
        }

        try {
            subjectService.save(subject)
        } catch (ValidationException e) {
            respond subject.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subject.text', default: 'Subject'), subject.id])
                redirect subject
            }
            '*' { respond subject, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond subjectService.get(id)
    }

    def update(Subject subject) {
        if (subject == null) {
            notFound()
            return
        }

        try {
            subjectService.save(subject)
        } catch (ValidationException e) {
            respond subject.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'subject.text', default: 'Subject'), subject.id])
                redirect subject
            }
            '*'{ respond subject, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        subjectService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'subject.text', default: 'Subject'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subject.text', default: 'Subject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
