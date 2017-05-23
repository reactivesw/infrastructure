package io.reactivesw.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application launcher.
 */
@SpringBootApplication(scanBasePackages = "io.reactivesw")
public class Application {

  /**
   * The entry point of application
   * @param args input argument.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
