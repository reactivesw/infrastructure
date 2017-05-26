package io.reactivesw.infrastructure.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.infrastructure.domain.model.Language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Language version validator.
 */
public class LanguageVersionValidator {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyVersionValidator.class);

  /**
   * Instantiates a new currency version validator
   */
  private LanguageVersionValidator() {
  }

  /**
   * Check version and entity.
   */
  public static void valiate(Language language, Integer version) {
    if (!language.getVersion().equals(version)) {
      LOGGER.debug("Version not match, input version: {}, entity version: {}.");
      throw new ConflictException("Version not match");
    }
  }
}
