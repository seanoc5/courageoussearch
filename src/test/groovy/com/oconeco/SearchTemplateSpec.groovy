package com.oconeco

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SearchTemplateSpec extends Specification implements DomainUnitTest<SearchTemplate> {
    List<String> flds = ['name', ]

    void "test nullable validation"() {
        given:
        flds.each { domain[(it)] = null }

        expect:
        !domain.validate(flds)
        flds.each {
            domain.errors[(it)].code == 'nullable'
        }

    }

    void "test minimal validation"() {
        given:
        flds.each { domain[(it)] = 'testval' }
        domain.save()

        expect:
        domain.validate()
    }


}
