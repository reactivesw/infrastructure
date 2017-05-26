package io.reactivesw.infrastructure.infrastructure.repository;

import io.reactivesw.infrastructure.domain.model.Language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Language repository.
 */
public interface LanguageRepostitory extends JpaRepository<Language, String> {

  /**
   * Query language by default language.
   *
   * @param defaultLanguage default language
   * @return list of language
   */
  List<Language> queryLanguagesByDefaultLanguage(Boolean defaultLanguage);
}
