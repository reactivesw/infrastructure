package io.reactivesw.infrastructure.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Language view is used to return response.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LanguageView {

  /**
   * The unique Id of the currency.
   */
  private String id;

  /**
   * The current version of the currency.
   */
  private Integer version;

  /**
   * ISO code.
   */
  private String isoCode;

  /**
   * Language name.
   */
  private String languageName;

  /**
   * Is it default language.
   */
  private boolean defaultLanguage;

  /**
   * Native name.
   */
  private String nativeName;
}
