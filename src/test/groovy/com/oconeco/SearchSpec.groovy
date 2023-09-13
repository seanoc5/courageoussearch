package com.oconeco

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SearchSpec extends Specification implements DomainUnitTest<Search> {

    def setup() {
    }

    def cleanup() {
    }

    void "test nullable validation"() {
        given:
        domain.additionalParams = 'testval'

        expect:
        !domain.validate(['query'])
        domain.errors['query'].code == 'nullable'
    }

    void "test minimal validation"() {
        given:
        domain.query = 'testval'
        domain.save()

        expect:
        domain.validate()
    }

}
