package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TagServiceSpec extends Specification {

    TagService tagService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Tag(...).save(flush: true, failOnError: true)
        //new Tag(...).save(flush: true, failOnError: true)
        //Tag tag = new Tag(...).save(flush: true, failOnError: true)
        //new Tag(...).save(flush: true, failOnError: true)
        //new Tag(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tag.id
    }

    void "test get"() {
        setupData()

        expect:
        tagService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Tag> tagList = tagService.list(max: 2, offset: 2)

        then:
        tagList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        tagService.count() == 5
    }

    void "test delete"() {
        Long tagId = setupData()

        expect:
        tagService.count() == 5

        when:
        tagService.delete(tagId)
        sessionFactory.currentSession.flush()

        then:
        tagService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Tag tag = new Tag()
        tagService.save(tag)

        then:
        tag.id != null
    }
}
