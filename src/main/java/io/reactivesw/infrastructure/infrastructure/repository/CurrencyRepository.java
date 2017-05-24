package io.reactivesw.infrastructure.infrastructure.repository;

import io.reactivesw.infrastructure.domain.model.Currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Currency Repository
 */
public interface CurrencyRepository extends JpaRepository<Currency, String> {

  /**
   * Query currency by default currency.
   *
   * @param isDefaultCurrency defaultCurrency
   * @return currency list
   */
  List<Currency> queryCurrencyByDefaultCurrency(boolean isDefaultCurrency);
}
