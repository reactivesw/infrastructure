package io.reactivesw.infrastructure.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Language Draft is used to receive request data.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageDraft {

  /**
   * The name of language.
   */
  @NotNull
  private String languageName;
  /**
   * Native name.
   */
  @NotNull
  private String nativeName;

  /**
   * ISO code.
   */
  @NotNull
  private String isoCode;
}
