package io.reactivesw.infrastructure.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

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
   * The current version of the category.
   */
  private Integer version;

  /**
   * Create time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * Last modified time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

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
