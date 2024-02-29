package com.afsheen.portfoliotracker;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ContextConfiguration(initializers = BaseIT.DataSourceInitializer.class)
public abstract class BaseIT {

    private static final MySQLContainer<?> database;

    static {
        // see https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers

        database = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.35")
                .asCompatibleSubstituteFor("mysql"))
                .withDatabaseName("portfolio")
                .withUsername("afs")
                .withPassword("afs");

        database.start();
    }

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                    "spring.test.database.replace=none",
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword());
        }
    }
}
