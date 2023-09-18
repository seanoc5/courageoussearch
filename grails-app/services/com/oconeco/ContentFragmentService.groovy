package com.oconeco

import grails.gorm.services.Service

@Service(ContentFragment)
interface ContentFragmentService {

    ContentFragment get(Serializable id)

    List<ContentFragment> list(Map args)

    Long count()

    void delete(Serializable id)

    ContentFragment save(ContentFragment contentFragment)

}