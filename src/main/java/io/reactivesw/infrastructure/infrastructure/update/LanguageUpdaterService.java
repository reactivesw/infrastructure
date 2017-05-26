package io.reactivesw.infrastructure.infrastructure.update;

import io.reactivesw.infrastructure.domain.model.Language;
import io.reactivesw.model.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * LanguageUpdaterService
 */
@Service
public class LanguageUpdaterService implements Updater<Language, UpdateAction> {

  /**
   * ApplicationContext for get update service.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * Put the value in action to entity
   *
   * @param language language
   * @param action updateAction
   */
  @Override
  public void handle(Language language, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(language, action);
  }

  /**
   * Get update service.
   *
   * @param updateAction updateAction
   * @return Updater
   */
  private Updater getUpdateService(UpdateAction updateAction) {
    return (Updater) context.getBean(updateAction.getActionName());
  }
}
