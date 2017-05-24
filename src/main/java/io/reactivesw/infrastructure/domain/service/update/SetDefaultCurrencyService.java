package io.reactivesw.infrastructure.domain.service.update;

import io.reactivesw.infrastructure.application.model.action.SetDefaultCurrency;
import io.reactivesw.infrastructure.domain.model.Currency;
import io.reactivesw.infrastructure.infrastructure.repository.CurrencyRepository;
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.util.InfrastructureActionUtils;
import io.reactivesw.model.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to set default currency when update currency.
 */
@Service(value = InfrastructureActionUtils.SET_DEFAULT_CURRENCY)
public class SetDefaultCurrencyService implements Updater<Currency, UpdateAction> {

  /**
   * Currency repository
   */
  private transient CurrencyRepository currencyRepository;

  /**
   * Instantiates a new SetDefaultCurrencyService.
   *
   * @param currencyRepository currency repository.
   */
  @Autowired
  public SetDefaultCurrencyService(CurrencyRepository currencyRepository) {
    this.currencyRepository = currencyRepository;
  }

  /**
   * Set default currency.
   *
   * @param currency currency entity
   * @param updateAction setDefaultCurrency action
   */
  @Override
  public void handle(Currency currency, UpdateAction updateAction) {
    SetDefaultCurrency setDefaultCurrency = (SetDefaultCurrency) updateAction;
    currency.setDefaultCurrency(setDefaultCurrency.getDefaultCurrency());
    unsetPreviousDefaultCurrency();
  }

  /**
   * Because this is only one default currency in system, so just unset previous
   * default currency before set a new default currency.
   */
  private void unsetPreviousDefaultCurrency() {
    boolean isDefaultCurrency = true;
    List<Currency> currencies = currencyRepository
        .queryCurrencyByDefaultCurrency(isDefaultCurrency);
    currencies.stream().forEach(currency -> {
      currency.setDefaultCurrency(false);
    });
    currencyRepository.save(currencies);
  }
}
