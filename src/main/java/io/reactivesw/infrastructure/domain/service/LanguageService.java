package io.reactivesw.infrastructure.domain.service;

import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.infrastructure.application.model.LanguageDraft;
import io.reactivesw.infrastructure.application.model.LanguageView;
import io.reactivesw.infrastructure.application.model.PagedQueryResult;
import io.reactivesw.infrastructure.application.model.mapper.LanguageMapper;
import io.reactivesw.infrastructure.domain.model.Language;
import io.reactivesw.infrastructure.infrastructure.repository.LanguageRepostitory;
import io.reactivesw.infrastructure.infrastructure.update.LanguageUpdaterService;
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.update.CurrencyUpdaterService;
import io.reactivesw.infrastructure.infrastructure.validator.LanguageVersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Language service.
 */
@Service
public class LanguageService {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);

  /**
   * LanguageRepository.
   */
  private transient LanguageRepostitory languageRepostitory;

  /**
   * Update service
   */
  private transient LanguageUpdaterService updateService;

  /**
   * Instantiates a new language service.
   *
   * @param languageRepostitory language repository
   * @param updateService update service
   */
  @Autowired
  public LanguageService(LanguageRepostitory languageRepostitory,
      LanguageUpdaterService updateService) {
    this.languageRepostitory = languageRepostitory;
    this.updateService = updateService;
  }

  /**
   * Add a new language.
   *
   * @param languageDraft language draft
   * @return language model
   */
  public LanguageView addLanguage(LanguageDraft languageDraft) {
    LOGGER.debug("Enter. Language draft: {}.", languageDraft);
    Language entity = LanguageMapper.toEntity(languageDraft);
    Language savedEntity = saveLanguageEntity(entity);
    LanguageView languageView = LanguageMapper.toModel(savedEntity);

    LOGGER.debug("Exit. New languageId: {}", languageView.getId());
    LOGGER.trace("New language: {}.", languageView);
    return languageView;
  }

  /**
   * Save language to database.
   *
   * @param language language entity
   * @return saved language entity
   */
  @Transactional
  private Language saveLanguageEntity(Language language) {
    Language savedLanguage = null;
    try {
      savedLanguage = languageRepostitory.save(language);
    } catch (DataIntegrityViolationException ex) {
      LOGGER.debug("Language exists.", ex);
      throw new AlreadyExistException("Language exists");
    }
    return savedLanguage;
  }

  /**
   * Query all language.
   *
   * @return list of language
   */
  public PagedQueryResult<LanguageView> queryAllLanguage() {
    LOGGER.debug("Enter.");
    List<Language> languages = languageRepostitory.findAll();
    List<LanguageView> result = LanguageMapper.toModel(languages);

    PagedQueryResult<LanguageView> pagedQueryResult = new PagedQueryResult<>();
    pagedQueryResult.setResults(result);
    pagedQueryResult.setTotal(result.size());
    LOGGER.debug("Exit. Queried language count: {}.", result.size());
    LOGGER.trace("Languages: {}.", result);
    return pagedQueryResult;
  }

  /**
   * Update language.
   *
   * @param id language id
   * @param version language version
   * @param actions update actions
   * @return langauge model
   */
  public LanguageView updateLanguage(String id, Integer version, List<UpdateAction> actions) {
    LOGGER.debug("Enter. LanguageId: {}, version: {}, actions: {}.", id, version, actions);
    Language language = getLanguageById(id);
    LanguageVersionValidator.valiate(language, version);

    Language updatedLanguage = updateLanguageEntity(actions, language);
    LanguageView result = LanguageMapper.toModel(updatedLanguage);
    LOGGER.debug("Exit. LanguageId: {}.", id);
    LOGGER.trace("Updated language: {}.", result);
    return result;
  }

  private Language getLanguageById(String id) {
    LOGGER.debug("Enter. Id: {}.", id);
    Language language = languageRepostitory.findOne(id);
    if (language == null) {
      LOGGER.debug("Get language by id failed, id: {}", id);
      throw new NotExistException("Can not find language by id: " + id);
    }
    LOGGER.debug("Exit. Id: {}.", id);
    LOGGER.trace("Language: {}.", id, language);
    return language;
  }

  private Language updateLanguageEntity(List<UpdateAction> actions, Language language) {
    actions.stream().forEach(action -> {
      updateService.handle(language, action);
    });
    return languageRepostitory.save(language);
  }
}
