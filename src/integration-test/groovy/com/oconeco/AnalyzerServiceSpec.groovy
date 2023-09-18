package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnalyzerServiceSpec extends Specification {

    AnalyzerService analyzerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Analyzer(...).save(flush: true, failOnError: true)
        //new Analyzer(...).save(flush: true, failOnError: true)
        //Analyzer analyzer = new Analyzer(...).save(flush: true, failOnError: true)
        //new Analyzer(...).save(flush: true, failOnError: true)
        //new Analyzer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //analyzer.id
    }

    void "test get"() {
        setupData()

        expect:
        analyzerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Analyzer> analyzerList = analyzerService.list(max: 2, offset: 2)

        then:
        analyzerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        analyzerService.count() == 5
    }

    void "test delete"() {
        Long analyzerId = setupData()

        expect:
        analyzerService.count() == 5

        when:
        analyzerService.delete(analyzerId)
        sessionFactory.currentSession.flush()

        then:
        analyzerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Analyzer analyzer = new Analyzer()
        analyzerService.save(analyzer)

        then:
        analyzer.id != null
    }
}
