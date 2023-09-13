package com.oconeco

import grails.gorm.services.Service

@Service(Context)
interface ContextService {

    Context get(Serializable id)

    List<Context> list(Map args)

    Long count()

    void delete(Serializable id)

    Context save(Context context)

}
