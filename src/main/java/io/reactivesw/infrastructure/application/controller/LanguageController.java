package io.reactivesw.infrastructure.application.controller;

import io.reactivesw.infrastructure.application.model.LanguageDraft;
import io.reactivesw.infrastructure.application.model.LanguageView;
import io.reactivesw.infrastructure.application.model.PagedQueryResult;
import io.reactivesw.infrastructure.domain.service.LanguageService;
import io.reactivesw.infrastructure.infrastructure.Router;
import io.reactivesw.infrastructure.infrastructure.update.UpdateRequest;
import io.reactivesw.infrastructure.infrastructure.validator.LanguageValidator;

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
 * Language controller.
 */
@RestController
public class LanguageController {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LanguageController.class);
  /**
   * Language service
   */
  private transient LanguageService languageService;

  /**
   * Instantiates a new language controller.
   *
   * @param languageService language service.
   */
  @Autowired
  public LanguageController(LanguageService languageService) {
    this.languageService = languageService;
  }

  /**
   * Add a new language.
   *
   * @param languageDraft language draft
   * @return language model
   */
  @PostMapping(Router.LANGUAGE_ROOT)
  public LanguageView addLanguage(@RequestBody @Valid LanguageDraft languageDraft) {
    LOGGER.info("Enter. Add Language: {}.", languageDraft);
    LanguageValidator.validateNull(languageDraft);
    LanguageView languageView = languageService.addLanguage(languageDraft);
    LOGGER.info("Exit. LanguageId: {}.", languageView.getId());
    LOGGER.trace("Language: {}.", languageView);
    return languageView;
  }

  /**
   * Query all language.
   *
   * @return list of language
   */
  @GetMapping(Router.LANGUAGE_ROOT)
  public PagedQueryResult<LanguageView> queryAllLanguage() {
    LOGGER.info("Enter.");
    PagedQueryResult<LanguageView> result = languageService.queryAllLanguage();

    LOGGER.info("Exit. Result count: {}.", result.getTotal());
    LOGGER.trace("Queried Result: {}.", result);
    return result;
  }

  /**
   * Update language.
   *
   * @param id languageId.
   * @param updateRequest updateRequest
   * @return language model
   */
  @PutMapping(Router.LANGUAGE_WITH_ID)
  public LanguageView updateLanguage(@PathVariable(value = Router.LANGUAGE_ID) String id,
      @RequestBody @Valid
          UpdateRequest updateRequest) {
    LOGGER.info("Enter. LanguageId: {}, update request.", id, updateRequest);
    LanguageView result = languageService
        .updateLanguage(id, updateRequest.getVersion(), updateRequest.getActions());
    LOGGER.info("Exit. LanguageId: {}.", result.getId());
    LOGGER.trace("Updated language: {}.", result);

    return result;
  }
}
