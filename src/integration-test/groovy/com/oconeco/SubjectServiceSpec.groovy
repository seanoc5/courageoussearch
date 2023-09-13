package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SubjectServiceSpec extends Specification {

    SubjectService subjectService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Subject(...).save(flush: true, failOnError: true)
        //new Subject(...).save(flush: true, failOnError: true)
        //Subject subject = new Subject(...).save(flush: true, failOnError: true)
        //new Subject(...).save(flush: true, failOnError: true)
        //new Subject(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //subject.id
    }

    void "test get"() {
        setupData()

        expect:
        subjectService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Subject> subjectList = subjectService.list(max: 2, offset: 2)

        then:
        subjectList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        subjectService.count() == 5
    }

    void "test delete"() {
        Long subjectId = setupData()

        expect:
        subjectService.count() == 5

        when:
        subjectService.delete(subjectId)
        sessionFactory.currentSession.flush()

        then:
        subjectService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Subject subject = new Subject()
        subjectService.save(subject)

        then:
        subject.id != null
    }
}
