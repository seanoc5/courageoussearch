package com.oconeco

class TopicController {

    TopicService topicService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static scaffold = Topic

    def autoComplete() {
//        List<Topic> topics = topicService.list(params)
        List<Topic> topics = Topic.findAllByLabelIlike("%${params.term}%")
        log.info "TopicController autocomplete, params: $params, results: ${topics*.label}"
        respond topics, model: [topicCount: topicService.count()]
    }

/*


    @Transactional
    def test() {
        Topic t1 = Topic.first()
        Concept c1 = Concept.first()
//        c1.add
        t1.add
        //respond topicService.test()
    }

    def show(Long id) {
        respond topicService.get(id)
    }

    def create() {
        respond new Topic(params)
    }

    def save(Topic topic) {
        if (topic == null) {
            notFound()
            return
        }

        try {
            topicService.save(topic)
        } catch (ValidationException e) {
            respond topic.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'topic.text', default: 'Topic'), topic.id])
                redirect topic
            }
            '*' { respond topic, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond topicService.get(id)
    }

    def update(Topic topic) {
        if (topic == null) {
            notFound()
            return
        }

        try {
            topicService.save(topic)
        } catch (ValidationException e) {
            respond topic.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'topic.text', default: 'Topic'), topic.id])
                redirect topic
            }
            '*' { respond topic, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        topicService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'topic.text', default: 'Topic'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.text', default: 'Topic'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
*/
}
