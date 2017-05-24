package io.reactivesw.infrastructure.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.infrastructure.domain.model.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Version validator.
 */
public final class CurrencyVersionValidator {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyVersionValidator.class);

  /**
   * Instantiates a new currency version validator
   */
  private CurrencyVersionValidator() {
  }

  /**
   * Check version and entity.
   */
  public static void valiate(Currency currency, Integer version) {
    if (!currency.getVersion().equals(version)) {
      LOGGER.debug("Version not match, input version: {}, entity version: {}.");
      throw new ConflictException("Version not match");
    }
  }
}
