package io.reactivesw.infrastructure.domain.service.update;

import io.reactivesw.infrastructure.application.model.action.SetDefaultLanguage;
import io.reactivesw.infrastructure.domain.model.Language;
import io.reactivesw.infrastructure.infrastructure.repository.LanguageRepostitory;
import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.util.InfrastructureActionUtils;
import io.reactivesw.model.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service used to set default language when update language.
 */
@Service(value = InfrastructureActionUtils.SET_DEFAULT_LANGUAGE)
public class SetDefaultLanguageService implements Updater<Language, UpdateAction> {

  /**
   * Language repository
   */
  private transient LanguageRepostitory languageRepostitory;

  /**
   * Instantiates a new SetDefaultLanguageService.
   *
   * @param languageRepostitory languageRepository
   */
  @Autowired
  public SetDefaultLanguageService(LanguageRepostitory languageRepostitory) {
    this.languageRepostitory = languageRepostitory;
  }

  /**
   * Set default language.
   *
   * @param language language
   * @param updateAction updateAction
   */
  @Override
  public void handle(Language language, UpdateAction updateAction) {
    unsetPreviousDefaultLanguage();
    SetDefaultLanguage setDefaultLanguage = (SetDefaultLanguage) updateAction;
    language.setDefaultLanguage(setDefaultLanguage.getDefaultLanguage());
  }


  /**
   * Because this is only one default language in system, so just unset previous
   * default language before set a new default language.
   */
  private void unsetPreviousDefaultLanguage() {
    boolean defaultLanguage = true;
    List<Language> languages = languageRepostitory.queryLanguagesByDefaultLanguage(defaultLanguage);
    languages.stream().forEach(language -> {
      language.setDefaultLanguage(false);
      languageRepostitory.saveAndFlush(language);
    });
  }
}
