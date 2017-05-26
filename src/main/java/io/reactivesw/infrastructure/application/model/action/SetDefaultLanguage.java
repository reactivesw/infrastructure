package io.reactivesw.infrastructure.application.model.action;

import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.util.InfrastructureActionUtils;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * SetDefaultLanguage is used to update language.
 */
@Getter
@Setter
public class SetDefaultLanguage implements UpdateAction {

  /**
   * Default language
   */
  @NotNull
  private Boolean defaultLanguage;

  /**
   * Get action name.
   *
   * @return action name
   */
  @Override
  public String getActionName() {
    return InfrastructureActionUtils.SET_DEFAULT_LANGUAGE;
  }

}
