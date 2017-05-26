package io.reactivesw.infrastructure.domain.service;

import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.infrastructure.application.model.CurrencyDraft;
import io.reactivesw.infrastructure.application.model.CurrencyView;
import io.reactivesw.infrastructure.application.model.PagedQueryResult;
import io.reactivesw.infrastructure.application.model.mapper.CurrencyMapper;
import io.reactivesw.infrastructure.domain.model.Currency;
import io.reactivesw.infrastructure.infrastructure.repository.CurrencyRepository;
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.update.CurrencyUpdaterService;
import io.reactivesw.infrastructure.infrastructure.validator.CurrencyVersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Currency service.
 */
@Service
public class CurrencyService {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

  /**
   * Currency Repository.
   */
  private transient CurrencyRepository currencyRepository;

  /**
   * Currency update service.
   */
  private transient CurrencyUpdaterService currencyUpdaterService;

  /**
   * Instantiates a new currency service.
   *
   * @param currencyRepository currencyRepository
   * @param currencyUpdaterService updateService
   */
  @Autowired
  public CurrencyService(CurrencyRepository currencyRepository, CurrencyUpdaterService currencyUpdaterService) {
    this.currencyRepository = currencyRepository;
    this.currencyUpdaterService = currencyUpdaterService;
  }


  /**
   * Query currency.
   *
   * @return queried currency.
   */
  public PagedQueryResult<CurrencyView> queryAllCurrency() {
    LOGGER.debug("Enter.");
    List<Currency> entities = currencyRepository.findAll();

    List<CurrencyView> result = CurrencyMapper.toModel(entities);
    LOGGER.debug("Exit. Queried currencies count: {}.", result.size());
    LOGGER.trace("Currencies: {}.", result);
    PagedQueryResult<CurrencyView> currencyViewPagedQueryResult = new PagedQueryResult<>();
    currencyViewPagedQueryResult.setResults(result);
    currencyViewPagedQueryResult.setTotal(result.size());
    return currencyViewPagedQueryResult;
  }

  /**
   * Add a new currency.
   *
   * @param currencyDraft currency draft.
   * @return currency view.
   */
  public CurrencyView addCurrency(CurrencyDraft currencyDraft) {
    LOGGER.debug("Enter. Currency Draft: {}.", currencyDraft);
    Currency entity = CurrencyMapper.toEntity(currencyDraft);
    Currency savedCurrency = saveCurrencyEntity(entity);
    CurrencyView currencyView = CurrencyMapper.toModel(savedCurrency);
    LOGGER.debug("Exit. New currencyId: {}.", currencyView.getId());
    LOGGER.trace("New currency: {}.", currencyView);
    return currencyView;
  }

  /**
   * Save currency.
   *
   * @param currency currency
   * @return currency entity
   */
  @Transactional
  private Currency saveCurrencyEntity(Currency currency) {
    Currency savedEntity = null;
    try {
      savedEntity = currencyRepository.save(currency);
    } catch (DataIntegrityViolationException ex) {
      LOGGER.debug("Currency is existed.", ex);
      throw new AlreadyExistException("Currency is existed");
    }
    return savedEntity;
  }

  /**
   * Update currency.
   *
   * @param id currency id
   * @param version currency version
   * @param actions update actions
   * @return currency view
   */
  public CurrencyView updateCurrency(String id, Integer version, List<UpdateAction> actions) {
    LOGGER.debug("Enter. CurrencyId: {}, version: {}, actions: {}.", id, version, actions);
    Currency currency = getById(id);
    CurrencyVersionValidator.valiate(currency, version);

    Currency updatedCurrency = updateCurrencyEntity(actions, currency);
    CurrencyView result = CurrencyMapper.toModel(updatedCurrency);
    LOGGER.debug("Exit. CurrencyId: {}.", id);
    LOGGER.trace("Updated currency: {}.", result);
    return result;
  }

  /**
   * Get currency by id.
   *
   * @param id currency id
   * @return currency entity
   */
  public Currency getById(String id) {

    LOGGER.debug("Enter. Id: {}.", id);
    Currency currency = currencyRepository.findOne(id);
    if (currency == null) {
      LOGGER.debug("Get entity by id failed, could not find entity by id: {}.", id);
      throw new NotExistException("Can not find currency by id:" + id);
    }
    LOGGER.debug("Exit. Id: {}.", id);
    LOGGER.trace("Currency: {}.", currency);
    return currency;
  }

  /**
   * Update currency entity.
   *
   * @param actions update actions
   * @param currency currency entity
   * @return saved currency entity
   */
  @Transactional
  private Currency updateCurrencyEntity(List<UpdateAction> actions, Currency currency) {
    actions.stream().forEach(action -> {
      currencyUpdaterService.handle(currency, action);
    });
    return currencyRepository.save(currency);
  }
}
