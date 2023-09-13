package com.oconeco

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ContentSpec extends Specification implements DomainUnitTest<Content> {

    def setup() {
    }

    def cleanup() {
    }

    void "test nullable validation"() {
        given:
        List<String> flds = ['title', 'uri']
        flds.each { domain[(it)] = null }

        expect:
        !domain.validate(flds)
        flds.each {
            domain.errors[(it)] == 'nullable'
        }

    }

    void "test minimal validation"() {
        given:
        domain.title = 'title'
        domain.uri = 'uri'
        domain.save()

        expect:
        domain.validate()
    }

}
