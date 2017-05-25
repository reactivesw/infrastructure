package io.reactivesw.infrastructure.application.model.mapper;

import io.reactivesw.infrastructure.application.model.CurrencyDraft;
import io.reactivesw.infrastructure.application.model.CurrencyView;
import io.reactivesw.infrastructure.domain.model.Currency;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Currency object converter.
 */
public final class CurrencyMapper {

  /**
   * Instantiates currency mapper.
   */
  private CurrencyMapper() {

  }

  /**
   * Convert entity to view.
   *
   * @param currency currency entity
   * @return currency view
   */
  public static CurrencyView toModel(Currency currency) {
    CurrencyView currencyView = new CurrencyView();
    currencyView.setId(currency.getId());
    currencyView.setDefaultCurrency(currency.isDefaultCurrency());
    currencyView.setIsoCode(currency.getIsoCode());
    currencyView.setVersion(currency.getVersion());
    currencyView.setCurrencyName(currency.getCurrencyName());
    currencyView.setConversionFactor(currency.getConversionFactor());
    return currencyView;
  }

  /**
   * Convert List of currency entity to currency view.
   *
   * @param currencies list of currency entity.
   * @return list of currency view.
   */
  public static List<CurrencyView> toModel(List<Currency> currencies) {
    return currencies.stream().map(CurrencyMapper::toModel).collect(Collectors.toList());
  }

  /**
   * Convert currency draft to currency entity.
   *
   * @param currencyDraft currency draft
   * @return currency entity
   */
  public static Currency toEntity(CurrencyDraft currencyDraft) {
    Currency currency = new Currency();
    currency.setCurrencyName(currencyDraft.getCurrencyName());
    currency.setIsoCode(currencyDraft.getIsoCode());
    currency.setConversionFactor(currencyDraft.getConversionFactor());
    return currency;
  }
}
