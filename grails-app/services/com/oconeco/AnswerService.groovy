package com.oconeco

import grails.gorm.services.Service

@Service(Answer)
interface AnswerService {

    Answer get(Serializable id)

    List<Answer> list(Map args)

    Long count()

    void delete(Serializable id)

    Answer save(Answer answer)

}