package com.oconeco

import grails.gorm.services.Service

@Service(SearchConfiguration)
interface SearchConfigurationService {

    SearchConfiguration get(Serializable id)

    List<SearchConfiguration> list(Map args)

    Long count()

    void delete(Serializable id)

    SearchConfiguration save(SearchConfiguration searchConfiguration)

}