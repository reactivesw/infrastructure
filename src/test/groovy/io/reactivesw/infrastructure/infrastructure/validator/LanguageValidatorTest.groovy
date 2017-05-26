package io.reactivesw.infrastructure.infrastructure.validator

import io.reactivesw.exception.ParametersException
import io.reactivesw.infrastructure.application.model.LanguageDraft
import spock.lang.Specification

/**
 * Language validator test.
 */
class LanguageValidatorTest extends Specification {
    LanguageValidator languageValidator = new LanguageValidator()

    def "Test1: entity's isoCode is empty, should throw a parametersException"() {
        given: "prepare data"
        LanguageDraft languageDraft = new LanguageDraft()
        languageDraft.languageName = "languageName"
        languageDraft.nativeName = "nativeName"

        when: "call function to validate"
        LanguageValidator.validateNull(languageDraft)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }

    def "Test2: entity's parameters are valid, no exception should throw"() {
        given: "prepare data"
        LanguageDraft languageDraft = new LanguageDraft()
        languageDraft.languageName = "languageName"
        languageDraft.isoCode = "iso_code"
        languageDraft.nativeName = "nativeName"

        when: "call function to validate"
        LanguageValidator.validateNull(languageDraft)

        then: "should throw a parametersException"
        noExceptionThrown()
    }

    def "Test3: entity's language name and isoCode are empty, should throw a parametersException"() {
        given: "prepare data"
        LanguageDraft languageDraft = new LanguageDraft()
        languageDraft.languageName = ""
        languageDraft.isoCode = ""
        languageDraft.nativeName = "nativeName"

        when: "call function to validate"
        LanguageValidator.validateNull(languageDraft)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }


    def "Test4: entity's native name is empty, should throw a parametersException"() {
        given: "prepare data"
        LanguageDraft languageDraft = new LanguageDraft()
        languageDraft.nativeName = ""
        languageDraft.languageName = "languageName"
        languageDraft.isoCode = "iso_code"

        when: "call function to validate"
        LanguageValidator.validateNull(languageDraft)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }

}
