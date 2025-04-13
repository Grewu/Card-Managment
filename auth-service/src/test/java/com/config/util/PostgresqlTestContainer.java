package com.config.util;

import org.junit.Rule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class PostgresqlTestContainer {
  @Rule
  private static final PostgreSQLContainer<?> container =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"));

  static {
    container.start();
  }

  @DynamicPropertySource
  static void setUrl(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
  }
}
