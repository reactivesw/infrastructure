package io.reactivesw.infrastructure.infrastructure.validator

import io.reactivesw.exception.ConflictException
import io.reactivesw.infrastructure.domain.model.Language
import spock.lang.Specification

/**
 * Language version validator
 */
class LanguageVersionValidatorTest extends Specification {
    LanguageVersionValidator languageVersionValidator = new LanguageVersionValidator()

    def "Test1: entity's version and input version doesn't match, shoud throw a conflictException"() {
        given: "prepare data"
        Language entity = new Language(version: 1)
        Integer version = 2

        when: "call function"
        LanguageVersionValidator.valiate(entity, version)

        then: "should throw a conflictException"
        thrown(ConflictException)
    }

    def "Test2: entity's version and input version matches, no exceptio is thrown"() {
        given: "prepare data"
        Integer version = 1
        Language entity = new Language(version: version)

        when: "call function"
        LanguageVersionValidator.valiate(entity, version)

        then: "no exception thrown"
        noExceptionThrown()
    }
}
