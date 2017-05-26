package io.reactivesw.infrastructure.domain.service

import io.reactivesw.exception.AlreadyExistException
import io.reactivesw.exception.NotExistException
import io.reactivesw.infrastructure.application.model.LanguageDraft
import io.reactivesw.infrastructure.application.model.action.SetDefaultLanguage
import io.reactivesw.infrastructure.domain.model.Language
import io.reactivesw.infrastructure.infrastructure.repository.LanguageRepostitory
import io.reactivesw.infrastructure.infrastructure.update.LanguageUpdaterService
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction
import org.assertj.core.util.Lists
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

/**
 * Language Service Test
 */
class LanguageServiceTest extends Specification {
    LanguageUpdaterService updaterService = Mock()
    LanguageRepostitory languageRepostitory = Mock(LanguageRepostitory)
    LanguageService languageService = new LanguageService(languageRepostitory, updaterService)
    def languageEntity = new Language()
    LanguageDraft languageDraft = new LanguageDraft()
    def version = 1
    def id = "fghjkl"

    def "Test1: get all languages"() {
        given: "prepare data"
        List<Language> languages = Lists.newArrayList(languageEntity)
        languageRepostitory.findAll() >> languages

        when: "call function to get languages"
        def result = languageService.queryAllLanguage()

        then: "result should not be null"
        result != null
    }

    def "Test2: add language"() {
        given: "prepare data"
        languageRepostitory.save(_) >> languageEntity

        when: "call function to add language"
        languageDraft.isoCode = "iso_code"
        languageDraft.languageName = "language_name"
        languageDraft.nativeName = "native_name"
        def language = languageService.addLanguage(languageDraft)

        then: "language should not be null"
        language != null
    }

    def "Test3: add duplicate language, should throw a AlreadyExistException"() {
        given: "prepare data"
        languageRepostitory.save(_) >> {
            throw new DataIntegrityViolationException("Could not execute statement")
        }

        when: "call function to add language"
        languageDraft.isoCode = "iso_code"
        languageDraft.languageName = "language_name"
        languageDraft.nativeName = "native_name"
        def language = languageService.addLanguage(languageDraft)


        then: "should throw an alreadyExistException"
        thrown(AlreadyExistException)
    }

    def "Test4: update language"() {
        given: "prepare data"
        def updateActions = new ArrayList<UpdateAction>()
        def updateDefaultLanguage = new SetDefaultLanguage(defaultLanguage: true)
        updateActions.add(updateDefaultLanguage)
        languageEntity.version = version
        languageRepostitory.findOne(_) >> languageEntity
        languageRepostitory.save(_) >> languageEntity
        updaterService.handle(_, _) >> null

        when: "call function to update language"
        def language = languageService.updateLanguage(id, version, updateActions)

        then:
        language != null
    }

    def "Test5: get language by id and get null entity"() {
        given: "prepare data"
        def updateActions = new ArrayList<UpdateAction>()
        languageRepostitory.findOne(_) >> null

        when: "call function to get language"
        languageService.updateLanguage(id, version, updateActions)

        then: "should throw a notExistException"
        thrown(NotExistException)
    }
}
