package com.oconeco

import grails.gorm.services.Service

@Service(ThingType)
interface ThingTypeService {

    ThingType get(Serializable id)

    List<ThingType> list(Map args)

    Long count()

    void delete(Serializable id)

    ThingType save(ThingType thingType)

}