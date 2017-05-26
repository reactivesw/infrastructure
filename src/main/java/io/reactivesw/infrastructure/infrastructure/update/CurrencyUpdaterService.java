package io.reactivesw.infrastructure.infrastructure.update;

import io.reactivesw.infrastructure.domain.model.Currency;
import io.reactivesw.model.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * We may get two kind of update: use the data stored in action, or use data from other service.
 * If we use the data stored in action, we can only use action mapper to set the data.
 * If we need get data from other service, we should use update service.
 */
@Service
public class CurrencyUpdaterService implements Updater<Currency, UpdateAction> {

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * Put the value in action to entity.
   *
   * @param currency currency.
   * @param updateAction updateAction
   */
  @Override
  public void handle(Currency currency, UpdateAction updateAction) {
    Updater updater =getUpdateService(updateAction);
    updater.handle(currency, updateAction);
  }

  /**
   * Get mapper.
   * @param action UpdateAction
   * @return UpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    return (Updater) context.getBean(action.getActionName());
  }
}
