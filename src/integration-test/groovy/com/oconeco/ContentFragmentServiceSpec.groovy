package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ContentFragmentServiceSpec extends Specification {

    ContentFragmentService contentFragmentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ContentFragment(...).save(flush: true, failOnError: true)
        //new ContentFragment(...).save(flush: true, failOnError: true)
        //ContentFragment contentFragment = new ContentFragment(...).save(flush: true, failOnError: true)
        //new ContentFragment(...).save(flush: true, failOnError: true)
        //new ContentFragment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //contentFragment.id
    }

    void "test get"() {
        setupData()

        expect:
        contentFragmentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ContentFragment> contentFragmentList = contentFragmentService.list(max: 2, offset: 2)

        then:
        contentFragmentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        contentFragmentService.count() == 5
    }

    void "test delete"() {
        Long contentFragmentId = setupData()

        expect:
        contentFragmentService.count() == 5

        when:
        contentFragmentService.delete(contentFragmentId)
        sessionFactory.currentSession.flush()

        then:
        contentFragmentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ContentFragment contentFragment = new ContentFragment()
        contentFragmentService.save(contentFragment)

        then:
        contentFragment.id != null
    }
}
