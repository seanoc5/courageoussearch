package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import groovy.json.JsonSlurper
import spock.lang.Specification
import org.hibernate.SessionFactory

import java.net.http.HttpResponse

@Integration
@Rollback
class SearchServiceSpec extends Specification {

    SearchService searchService
    SessionFactory sessionFactory
    List<Search> getDomainClasses() { [Search, SearchTemplate, SearchConfiguration, SearchResult] }

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        new Search(query: "1 How can I bulk load data into grail?" ).save(failOnError: true)
        new Search(query: "2 What does the color blue taste like?" ).save(failOnError: true)
        new Search(query: "3 what is foo?", searchEngine: "Brave", ).save(failOnError: true)

        Search flushSearch = new Search(query: "4 Is h2 db good?", searchEngine: "Brave", ).save(flush: true, failOnError: true)
        assert flushSearch.id !=null, 'Should have a valid id'
        return flushSearch.id
    }

    def "test execute basic (brave) template "() {
        given:
        Map hdrs = ["Accept": "application/json", "X-Subscription-Token": "BSAUlGxLKl1TEnzyzhH755sBpllHQIw"]
        JsonSlurper slurper = new JsonSlurper()
        SearchConfiguration config = new SearchConfiguration(label: "spock brave config", url: "https://api.search.brave.com/res/v1/web/search", headers: hdrs ).save(failOnError: true)
        SearchTemplate template = new SearchTemplate(label: "spock brave template").save(failOnError: true)
        template.addToSearchConfigs(config)
        Search search = new Search(query: "grails best practices 2023")
        search.addToSearchTemplates(template)
        search.save()

        when:
//        HttpResponse<String> response = searchService.execute(testsearch)
        def foo = searchService.execute(search)
//        String body = response.body()
//        def json = slurper.parseText(body)

        then:
        foo !=null
        foo.id != null
        foo.searchResults.size() ==1
//        json != null
//        json.type != null
//        json.web.results !=null

    }


    // --------------------- Stadard tests ---------------------
    void "test get"() {
        setupData()

        expect:
        searchService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Search> searchList = searchService.list(max: 2, offset: 2)

        then:
        searchList.size() == 2
//        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        searchService.count() == 4
    }

    void "test delete"() {
        Long searchId = setupData()

        expect:
        searchService.count() == 4
        def list = searchService.list()
        Search search = list[0]

        when:
        searchService.delete(search.id)
        sessionFactory.currentSession.flush()

        then:
        searchService.count() == 3
    }

    void "test save"() {
        when:
        Search search = new Search(query: 'testval')
        searchService.save(search)

        then:
        search.id != null
    }
}
