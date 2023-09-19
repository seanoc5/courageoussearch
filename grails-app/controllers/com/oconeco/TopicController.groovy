package com.oconeco

class TopicController {

    TopicService topicService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static scaffold = Topic

    def autoComplete() {
//        List<Topic> topics = topicService.list(params)
        List<Topic> topics = Topic.findAllByLabelIlike("%${params.term}%")
        log.debug "TopicController autocomplete, params: $params, results: ${topics*.label}"
        respond topics, model: [topicCount: topicService.count()]
    }

}
