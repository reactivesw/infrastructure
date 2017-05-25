package io.reactivesw.infrastructure.infrastructure.update;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import io.reactivesw.infrastructure.application.model.action.SetDefaultCurrency;
import io.reactivesw.infrastructure.application.model.action.SetDefaultLanguage;

/**
 * Configuration for common update actions.
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "action")

@JsonSubTypes({
    @JsonSubTypes.Type(value = SetDefaultCurrency.class, name = "setDefaultCurrency"),
    @JsonSubTypes.Type(value = SetDefaultLanguage.class, name = "setDefaultLanguage")
})
public interface UpdateAction {

  /**
   * Get action name.
   *
   * @return action name
   */
  String getActionName();
}
