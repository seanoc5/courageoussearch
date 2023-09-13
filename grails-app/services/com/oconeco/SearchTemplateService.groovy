package com.oconeco

import grails.gorm.services.Service

@Service(SearchTemplate)
interface SearchTemplateService {

    SearchTemplate get(Serializable id)

    List<SearchTemplate> list(Map args)

    Long count()

    void delete(Serializable id)

    SearchTemplate save(SearchTemplate searchTemplate)

}