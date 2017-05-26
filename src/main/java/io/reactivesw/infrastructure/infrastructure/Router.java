package io.reactivesw.infrastructure.infrastructure;

/**
 * Url for infrastructure service.
 */
public class Router {

  /**
   * The root path of infrastructure service.
   */
  public static final String INFRASTRUCTURE_ROOT = "/";
  /**
   * The path to check health status of infrastructure service.
   */
  public static final String INFRASTRUCTURE_HEALTH_CHECK = INFRASTRUCTURE_ROOT + "health";

  /**
   * The root path of currency
   */
  public static final String CURRENCY_ROOT = INFRASTRUCTURE_ROOT + "currency";
  /**
   * Currency id.
   */
  public static final String CURRENCY_ID = "currencyId";
  /**
   * Currency with id.
   */
  public static final String CURRENCY_WITH_ID = CURRENCY_ROOT + "/{" + CURRENCY_ID + "}";
  /**
   * The root path of language.
   */
  public static final String LANGUAGE_ROOT = INFRASTRUCTURE_ROOT + "language";
  /**
   * Language id.
   */
  public static final String LANGUAGE_ID = "languageID";

  /**
   * Language with id.
   */
  public static final String LANGUAGE_WITH_ID = LANGUAGE_ROOT + "/{" + LANGUAGE_ID + "}";
}
