package io.reactivesw.infrastructure.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Language Entity.
 */
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "language")
@EntityListeners(AuditingEntityListener.class)
public class Language {

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
   * Express the language name in this language.
   */
  @Column(name = "native_name")
  private String nativeName;

  /**
   * Name of language.
   */
  @Column(name = "language_name", unique = true)
  private String languageName;

  /**
   * Version
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * Default language.
   */
  @Column(name = "default_language")
  private boolean defaultLanguage;
}
