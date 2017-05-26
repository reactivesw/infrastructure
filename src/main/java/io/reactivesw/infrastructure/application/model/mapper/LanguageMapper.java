package io.reactivesw.infrastructure.application.model.mapper;

import io.reactivesw.infrastructure.application.model.LanguageDraft;
import io.reactivesw.infrastructure.application.model.LanguageView;
import io.reactivesw.infrastructure.domain.model.Language;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Language object converter.
 */
public final class LanguageMapper {

  /**
   * Constructor.
   */
  private LanguageMapper() {
  }

  /**
   * Convert Language entity to Language model.
   *
   * @param entity language entity
   * @return language model
   */
  public static LanguageView toModel(Language entity) {
    LanguageView languageView = new LanguageView();
    languageView.setId(entity.getId());
    languageView.setIsoCode(entity.getIsoCode());
    languageView.setLanguageName(entity.getLanguageName());
    languageView.setVersion(entity.getVersion());
    languageView.setNativeName(entity.getNativeName());
    languageView.setDefaultLanguage(entity.isDefaultLanguage());
    return languageView;
  }

  /**
   * Convert list of language entity to list of language model.
   *
   * @param entities list of language entity
   * @return list of language model
   */
  public static List<LanguageView> toModel(List<Language> entities) {
    return entities.stream().map(LanguageMapper::toModel).collect(Collectors.toList());
  }

  /**
   * Convert language draft to language entity.
   *
   * @param languageDraft language draft
   * @return language entity
   */
  public static Language toEntity(LanguageDraft languageDraft) {
    Language language = new Language();
    language.setIsoCode(languageDraft.getIsoCode());
    language.setLanguageName(languageDraft.getLanguageName());
    language.setNativeName(languageDraft.getNativeName());
    return language;
  }
}
