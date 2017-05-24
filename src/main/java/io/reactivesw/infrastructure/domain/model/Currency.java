package io.reactivesw.infrastructure.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Currency Entity.
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "currency")
public class Currency {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * Create at.
   */
  @CreatedDate
  @Column(name = "created_at")
  private ZonedDateTime createAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  private ZonedDateTime lastModifiedAt;

  /**
   * Code of iso 4217
   */
  @Column(name = "iso_code", unique = true)
  private String isoCode;

  /**
   * The conversion factor is used to express the relationship between a major
   * currency unit and its corresponding minor currency unit.
   */
  @Column(name = "conversion_factor")
  private int conversionFactor;

  /**
   * Name of currency.
   */
  @Column(name = "currency_name", unique = true)
  private String currencyName;

  /**
   * Version
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * Default currency.
   */
  @Column(name = "default_currency")
  private boolean defaultCurrency;
}

