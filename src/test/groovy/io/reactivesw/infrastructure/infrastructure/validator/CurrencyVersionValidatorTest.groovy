package io.reactivesw.infrastructure.infrastructure.validator

import io.reactivesw.exception.ConflictException
import io.reactivesw.infrastructure.domain.model.Currency
import spock.lang.Specification

/**
 * Test for currencyVersionValidator.
 */
class CurrencyVersionValidatorTest extends Specification {
    CurrencyVersionValidator validator = new CurrencyVersionValidator()

    def "Test1: entity's version and input version doesn't match, shoud throw a conflictException"() {
        given: "prepare data"
        Currency entity = new Currency(version: 1)
        Integer version=2

        when:"call function"
        CurrencyVersionValidator.valiate(entity,version)

        then:"should throw a conflictException"
        thrown(ConflictException)
    }
    def "Test2: entity's version and input version matches, no exceptio is thrown"(){
        given:"prepare data"
        Integer version=1
        Currency entity=new Currency(version:version)

        when:"call function"
        CurrencyVersionValidator.valiate(entity,version)

        then:"no exception thrown"
        noExceptionThrown()
    }
}
