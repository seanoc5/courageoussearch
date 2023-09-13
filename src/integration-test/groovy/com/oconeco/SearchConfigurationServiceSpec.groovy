package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SearchConfigurationServiceSpec extends Specification {

    SearchConfigurationService searchConfigurationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SearchConfiguration(...).save(flush: true, failOnError: true)
        //new SearchConfiguration(...).save(flush: true, failOnError: true)
        //SearchConfiguration searchConfiguration = new SearchConfiguration(...).save(flush: true, failOnError: true)
        //new SearchConfiguration(...).save(flush: true, failOnError: true)
        //new SearchConfiguration(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //searchConfiguration.id
    }

    void "test get"() {
        setupData()

        expect:
        searchConfigurationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SearchConfiguration> searchConfigurationList = searchConfigurationService.list(max: 2, offset: 2)

        then:
        searchConfigurationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        searchConfigurationService.count() == 5
    }

    void "test delete"() {
        Long searchConfigurationId = setupData()

        expect:
        searchConfigurationService.count() == 5

        when:
        searchConfigurationService.delete(searchConfigurationId)
        sessionFactory.currentSession.flush()

        then:
        searchConfigurationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SearchConfiguration searchConfiguration = new SearchConfiguration()
        searchConfigurationService.save(searchConfiguration)

        then:
        searchConfiguration.id != null
    }
}
