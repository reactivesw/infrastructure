package io.reactivesw.infrastructure.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.infrastructure.application.model.CurrencyDraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for currency.
 */
public final class CurrencyValidator {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyValidator.class);

  /**
   * Instantiates a new currency validator.
   */
  private CurrencyValidator() {

  }

  /**
   * Validate whether isoCode and name of currency are null or not.
   *
   * @param currencyDraft currency draft.
   */
  public static void validateNull(CurrencyDraft currencyDraft) {
    if (currencyDraft.getIsoCode() == null
        || currencyDraft.getIsoCode().isEmpty()
        ) {
      LOGGER.debug("Currency ISO code could not be null.");
      throw new ParametersException("Currency ISO code could not be null");
    }
    if (currencyDraft.getCurrencyName() == null
        || currencyDraft.getCurrencyName().isEmpty()) {
      LOGGER.debug("Currency name could not be null.");
      throw new ParametersException("Currency name could not be null.");
    }
  }

}
