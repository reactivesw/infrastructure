package io.reactivesw.infrastructure.application.model.action;

import io.reactivesw.infrastructure.infrastructure.update.UpdateAction;
import io.reactivesw.infrastructure.infrastructure.util.InfrastructureActionUtils;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * SetDefaultCurrencyService is used to update currency.
 */
@Getter
@Setter
public class SetDefaultCurrency implements UpdateAction {

  /**
   * Default currency.
   */
  @NotNull
  private Boolean defaultCurrency;

  /**
   * Get action name.
   *
   * @return update service name.
   */
  @Override
  public String getActionName() {
    return InfrastructureActionUtils.SET_DEFAULT_CURRENCY;
  }
}
