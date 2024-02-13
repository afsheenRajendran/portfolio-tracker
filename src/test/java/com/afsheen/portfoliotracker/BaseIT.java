package com.afsheen.portfoliotracker;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ContextConfiguration(initializers = BaseIT.DataSourceInitializer.class)
public abstract class BaseIT {
    @Container
    private static final MySQLContainer<?> database = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.35")
            .asCompatibleSubstituteFor("mysql"))
            .withDatabaseName("portfolio")
            .withUsername("afs")
            .withPassword("afs");

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
