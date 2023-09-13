package com.oconeco

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ThingTypeServiceSpec extends Specification {

    ThingTypeService thingTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ThingType(...).save(flush: true, failOnError: true)
        //new ThingType(...).save(flush: true, failOnError: true)
        //ThingType thingType = new ThingType(...).save(flush: true, failOnError: true)
        //new ThingType(...).save(flush: true, failOnError: true)
        //new ThingType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //thingType.id
    }

    void "test get"() {
        setupData()

        expect:
        thingTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ThingType> thingTypeList = thingTypeService.list(max: 2, offset: 2)

        then:
        thingTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        thingTypeService.count() == 5
    }

    void "test delete"() {
        Long thingTypeId = setupData()

        expect:
        thingTypeService.count() == 5

        when:
        thingTypeService.delete(thingTypeId)
        sessionFactory.currentSession.flush()

        then:
        thingTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ThingType thingType = new ThingType()
        thingTypeService.save(thingType)

        then:
        thingType.id != null
    }
}
