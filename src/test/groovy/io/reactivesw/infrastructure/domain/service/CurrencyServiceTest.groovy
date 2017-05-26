package io.reactivesw.infrastructure.domain.service

import io.reactivesw.exception.AlreadyExistException
import io.reactivesw.exception.NotExistException
import io.reactivesw.infrastructure.application.model.CurrencyDraft
import io.reactivesw.infrastructure.application.model.action.SetDefaultCurrency
import io.reactivesw.infrastructure.domain.model.Currency
import io.reactivesw.infrastructure.infrastructure.repository.CurrencyRepository
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction
import io.reactivesw.infrastructure.infrastructure.update.CurrencyUpdaterService
import org.assertj.core.util.Lists
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

/**
 * Currency Service Test.
 */
class CurrencyServiceTest extends Specification {
    CurrencyUpdaterService updateService = Mock()
    CurrencyRepository currencyRepository = Mock(CurrencyRepository)
    CurrencyService currencyService = new CurrencyService(currencyRepository, updateService)
    def currencyEntity = new Currency()
    CurrencyDraft currencyDraft = new CurrencyDraft()
    def version = 1
    def id = "dfghjkrt"

    def "Test1: get all currency"() {
        given: "prepare data"
        List<Currency> currencies = Lists.newArrayList(currencyEntity)
        currencyRepository.findAll() >> currencies

        when: "call function to get all currency"
        def result = currencyService.queryAllCurrency()

        then: "result should not be null"
        result != null
        result.results.size() == currencies.size()
    }

    def "Test2: add currency"() {
        given: "prepare data"
        currencyRepository.save(_) >> currencyEntity

        when: "call function to add currency"
        currencyDraft.isoCode = "ISOCode"
        currencyDraft.conversionFactor = 100
        currencyDraft.currencyName = "currencyName"
        def currency = currencyService.addCurrency(currencyDraft)

        then: "currency should be not null"
        currency != null
    }

    def "Test3: add duplicate currency, should throw a AlreadyExistException"() {
        given: "prepare data"
        currencyRepository.save(_) >> {
            throw new DataIntegrityViolationException("Could not execute statement")
        }

        when: "call function to add currency"
        currencyDraft.currencyName = "fad"
        currencyDraft.isoCode = "fad"
        currencyService.addCurrency(currencyDraft)

        then: "should throw an alreadyExistException"
        thrown(AlreadyExistException)
    }

    def "Test4: update currency"() {
        given: "prepare data"
        def updateActions = new ArrayList<UpdateAction>()
        def updateDefaultCurrency = new SetDefaultCurrency(defaultCurrency: true)
        updateActions.add(updateDefaultCurrency)
        currencyEntity.version = version
        currencyRepository.findOne(_) >> currencyEntity
        currencyRepository.save(_) >> currencyEntity
        updateService.handle(_, _) >> null

        when: "call function to update"
        def currency = currencyService.updateCurrency(id, version, updateActions)

        then:
        currency != null
    }

    def "Test5: get currency by id and get null entity"() {
        given: "prepare data"
        currencyRepository.findOne(_) >> null

        when: "call function to get currency"
        currencyService.getById(id)

        then: "should throw a notExistException"
        thrown(NotExistException)
    }
}
