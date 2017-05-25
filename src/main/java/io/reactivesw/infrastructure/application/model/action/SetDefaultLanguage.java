package io.reactivesw.infrastructure.application.model.action;

import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.util.InfrastructureActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * SetDefaultLanguage is used to update language.
 */
@Getter
@Setter
public class SetDefaultLanguage implements UpdateAction {

  /**
   * Default language
   */
  private boolean defaultLanguage;

  @Override
  public String getActionName() {
    return InfrastructureActionUtils.SET_DEFAULT_LANGUAGE;
  }

}
