package io.reactivesw.infrastructure.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.infrastructure.application.model.LanguageDraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LanguageValidator
 */
public final class LanguageValidator {


  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LanguageValidator.class);

  /**
   * Instantiates a new languag validator.
   */
  private LanguageValidator() {

  }

  /**
   * Validate whether isoCode, languageName, nativeName are empty.
   *
   * @param languageDraft language draft
   */
  public static void validateNull(LanguageDraft languageDraft) {
    if (languageDraft.getIsoCode() == null ||
        languageDraft.getIsoCode().isEmpty()) {
      LOGGER.debug("Language ISO code could not be null or empty.");
      throw new ParametersException("Language ISO code could not be null or empty.");
    }
    if (languageDraft.getLanguageName() == null ||
        languageDraft.getLanguageName().isEmpty()) {
      LOGGER.debug("Language name could not be null or empty.");
      throw new ParametersException("Language name could not be null or empty.");
    }
    if (languageDraft.getNativeName() == null ||
        languageDraft.getNativeName().isEmpty()) {
      LOGGER.debug("The native name of language could not be null or empty.");
      throw new ParametersException("The native name of language could not be null or empty.");
    }
  }
}
