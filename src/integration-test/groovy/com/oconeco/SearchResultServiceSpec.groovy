package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SearchResultServiceSpec extends Specification {

    SearchResultService searchResultService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SearchResult(...).save(flush: true, failOnError: true)
        //new SearchResult(...).save(flush: true, failOnError: true)
        //SearchResult searchResult = new SearchResult(...).save(flush: true, failOnError: true)
        //new SearchResult(...).save(flush: true, failOnError: true)
        //new SearchResult(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //searchResult.id
    }

    void "test get"() {
        setupData()

        expect:
        searchResultService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SearchResult> searchResultList = searchResultService.list(max: 2, offset: 2)

        then:
        searchResultList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        searchResultService.count() == 5
    }

    void "test delete"() {
        Long searchResultId = setupData()

        expect:
        searchResultService.count() == 5

        when:
        searchResultService.delete(searchResultId)
        sessionFactory.currentSession.flush()

        then:
        searchResultService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SearchResult searchResult = new SearchResult()
        searchResultService.save(searchResult)

        then:
        searchResult.id != null
    }
}
