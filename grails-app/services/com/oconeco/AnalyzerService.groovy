package com.oconeco

import grails.gorm.services.Service

@Service(Analyzer)
interface AnalyzerService {

    Analyzer get(Serializable id)

    List<Analyzer> list(Map args)

    Long count()

    void delete(Serializable id)

    Analyzer save(Analyzer analyzer)

}