package io.reactivesw.infrastructure.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
   * Code of iso 4217
   */
  @Column(name = "iso_code")
  private String ISOCode;

  /**
   * The conversion factor is used to express the relationship between a major
   * currency unit and its corresponding minor currency unit.
   */
  @Column(name = "conversion_factor")
  private int conversionFactor;

  /**
   * Name of currency.
   */
  @Column(name = "currency_name")
  private String currency;
}

