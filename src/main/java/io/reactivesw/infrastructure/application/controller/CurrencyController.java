package io.reactivesw.infrastructure.application.controller;

import io.reactivesw.infrastructure.application.model.CurrencyDraft;
import io.reactivesw.infrastructure.application.model.CurrencyView;
import io.reactivesw.infrastructure.application.model.PagedQueryResult;
import io.reactivesw.infrastructure.domain.service.CurrencyService;
import io.reactivesw.infrastructure.infrastructure.Router;
import io.reactivesw.infrastructure.infrastructure.update.UpdateRequest;
import io.reactivesw.infrastructure.infrastructure.validator.CurrencyValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Currency controller.
 */
@RestController
public class CurrencyController {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);

  /**
   * Currency service.
   */
  private transient CurrencyService currencyService;


  /**
   * Instantiates a new currency controller.
   *
   * @param currencyService currency service
   */
  @Autowired
  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  /**
   * Get all currency.
   *
   * @return list of currency
   */
  @GetMapping(Router.CURRENCY_ROOT)
  public PagedQueryResult<CurrencyView> queryAllCurrency() {
    LOGGER.info("Enter.");
    PagedQueryResult<CurrencyView> result = currencyService.queryAllCurrency();

    LOGGER.info("Exit. Result count: {}.", result.getCount());
    LOGGER.trace("Query result: {}.", result);
    return result;
  }

  /**
   * Add currency.
   *
   * @param currencyDraft currency draft.
   * @return currency view
   */
  @PostMapping(Router.CURRENCY_ROOT)
  public CurrencyView addCurrency(@RequestBody @Valid CurrencyDraft currencyDraft) {
    LOGGER.info("Enter. Add currency: {}.", currencyDraft);

    CurrencyValidator.validateNull(currencyDraft);
    CurrencyView currencyView = currencyService.addCurrency(currencyDraft);

    LOGGER.info("Exit. CurrencyId: {}.", currencyView.getId());
    LOGGER.trace("Currency: {}.", currencyView);
    return currencyView;
  }

  /**
   * Update currency.
   *
   * @param id currency id.
   * @param updateRequet update request
   * @return currency view
   */
  @PutMapping(Router.CURRENCY_WITH_ID)
  public CurrencyView updateCurrency(@PathVariable(value = Router.CURRENCY_ID) String id,
      @RequestBody @Valid UpdateRequest updateRequet) {
    LOGGER.info("Enter. CurrencyId: {}, update request: {}.", id, updateRequet);
    CurrencyView result = currencyService
        .updateCurrency(id, updateRequet.getVersion(), updateRequet.getActions());
    LOGGER.info("Exit. CurrencyId: {}.", result.getId());
    LOGGER.trace("Updated currency: {}.", result);
    return result;
  }
}
