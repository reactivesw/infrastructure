package io.reactivesw.infrastructure.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Currency draft is used to transfer data.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDraft {

  /**
   * Iso code.
   */
  @NotNull
  private String isoCode;

  /**
   * Name of currency.
   */
  @NotNull
  private String currencyName;

  /**
   * The conversion factor is used to express the relationship between a major
   * currency unit and its corresponding minor currency unit.
   */
  @NotNull
  private int conversionFactor;
}
