package io.reactivesw.infrastructure.infrastructure.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Object to update currency.
 */
public class UpdateRequest {

  /**
   * The expected version of the category on which the changes should be applied.
   * If the expected version does not match the actual version, a 409 Conflict will be returned.
   */
  @NotNull
  @Min(0)
  @Getter
  @Setter
  private Integer version;

  /**
   * Array of UpdateAction.
   * The list of update action to be performed on the category.
   * Required.
   */
  @NotNull
  @Valid
  private List<UpdateAction> actions;

  /**
   * Sets actions.
   *
   * @param actions the actions
   */
  public void setActions(List<UpdateAction> actions) {
    this.actions = actions;
  }

  /**
   * convert to UpdateActions.
   *
   * @return list of UpdateAction
   */
  public List<UpdateAction> getActions() {
    return actions.stream().map(action -> (UpdateAction) action).collect(Collectors.toList());
  }
}
