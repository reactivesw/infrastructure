package io.reactivesw.infrastructure.infrastructure.validator

import io.reactivesw.exception.ParametersException
import io.reactivesw.infrastructure.application.model.CurrencyDraft
import spock.lang.Specification

/**
 * Currency Validator Test
 */
class CurrencyValidatorTest extends Specification {
    CurrencyValidator currencyValidator = new CurrencyValidator()

    def "Test1: entity's isoCode is empty, should throw a parametersException"() {
        given: "prepare data"
        CurrencyDraft currencyDraft = new CurrencyDraft()
        currencyDraft.isoCode = ""
        currencyDraft.currencyName = "name"

        when: "call function to validate"
        CurrencyValidator.validateNull(currencyDraft)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }

    def "Test2: entity's isoCode is not null, should pass validation"() {
        given: "prepare data"
        CurrencyDraft currencyDraft = new CurrencyDraft()
        currencyDraft.isoCode = "isoCode"
        currencyDraft.currencyName = "name"

        when: "call function to validate"
        CurrencyValidator.validateNull(currencyDraft)

        then:
        noExceptionThrown()
    }

    def "Test3: entity's currencyName is empty, should throw a parametersException"() {
        given: "prepare data"
        CurrencyDraft currencyDraft = new CurrencyDraft()
        currencyDraft.currencyName = ""
        currencyDraft.isoCode = "isoCode"

        when: "call function to validate"
        CurrencyValidator.validateNull(currencyDraft)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }

    def "Test4: entity's currencyName is not null, should pass validation"() {
        given: "prepare data"
        CurrencyDraft currencyDraft = new CurrencyDraft()
        currencyDraft.isoCode = "isoCode"
        currencyDraft.currencyName = "name"

        when: "call function to validate"
        CurrencyValidator.validateNull(currencyDraft)

        then:
        noExceptionThrown()
    }
}
