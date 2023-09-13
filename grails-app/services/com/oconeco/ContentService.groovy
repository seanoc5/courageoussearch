package com.oconeco

import grails.gorm.services.Service

@Service(Content)
interface ContentService {

    Content get(Serializable id)

    List<Content> list(Map args)

    Long count()

    void delete(Serializable id)

    Content save(Content content)

}