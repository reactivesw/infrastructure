package io.reactivesw.infrastructure.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Currency model.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CurrencyView {

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
   * Currency name.
   */
  private String currencyName;

  /**
   * Is it default currency.
   */
  private boolean defaultCurrency;

  /**
   * Conversion factor.
   */
  private Integer conversionFactor;
}
