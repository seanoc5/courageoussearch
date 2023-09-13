package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SearchTemplateServiceSpec extends Specification {

    SearchTemplateService searchTemplateService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SearchTemplate(...).save(flush: true, failOnError: true)
        //new SearchTemplate(...).save(flush: true, failOnError: true)
        //SearchTemplate searchTemplate = new SearchTemplate(...).save(flush: true, failOnError: true)
        //new SearchTemplate(...).save(flush: true, failOnError: true)
        //new SearchTemplate(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //searchTemplate.id
    }

    void "test get"() {
        setupData()

        expect:
        searchTemplateService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SearchTemplate> searchTemplateList = searchTemplateService.list(max: 2, offset: 2)

        then:
        searchTemplateList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        searchTemplateService.count() == 5
    }

    void "test delete"() {
        Long searchTemplateId = setupData()

        expect:
        searchTemplateService.count() == 5

        when:
        searchTemplateService.delete(searchTemplateId)
        sessionFactory.currentSession.flush()

        then:
        searchTemplateService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SearchTemplate searchTemplate = new SearchTemplate()
        searchTemplateService.save(searchTemplate)

        then:
        searchTemplate.id != null
    }
}
