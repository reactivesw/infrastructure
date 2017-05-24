package io.reactivesw.infrastructure.application.controller;

import io.reactivesw.infrastructure.infrastructure.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to check health status of service.
 */
@RestController
public class HealthController {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

  /**
   * Service name.
   */

  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * This api is used to get health status.
   *
   * @return service name
   */
  @GetMapping(Router.INFRASTRUCTURE_HEALTH_CHECK)
  public String healthCheck() {
    LOGGER.info("Enter.");

    long currentTime = System.currentTimeMillis();

    LOGGER.info("Exit. Service name: {}, current time: {}.", serviceName, currentTime);
    return serviceName + ", system time: " + currentTime;
  }
}
