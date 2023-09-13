package com.oconeco

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface ISubjectService {

    Subject get(Serializable id)

    List<Subject> list(Map args)

    Long count()

    void delete(Serializable id)

    Subject save(Subject subject)

    Subject find(String label)

}

@Service(Subject)
abstract class SubjectService implements ISubjectService {
}
