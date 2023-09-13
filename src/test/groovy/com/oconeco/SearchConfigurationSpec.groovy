package com.oconeco

import grails.testing.gorm.DomainUnitTest
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class SearchConfigurationSpec extends Specification implements DomainUnitTest<SearchConfiguration> {

    List<String> flds = ['name', 'url']

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
        flds.each { domain[(it)] = 'test' }
        domain.save()

        expect:
        domain.validate()
    }


}
