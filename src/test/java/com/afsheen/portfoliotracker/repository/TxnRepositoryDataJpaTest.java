package com.afsheen.portfoliotracker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.afsheen.portfoliotracker.entity.Txn;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = TxnRepositoryDataJpaTest.DataSourceInitializer.class)
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.hibernate.show_sql=true"
})
public class TxnRepositoryDataJpaTest {

    Logger logger = LoggerFactory.getLogger(TxnRepositoryDataJpaTest.class);
    
    @Container
    private static final MySQLContainer<?> database = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.35")
            .asCompatibleSubstituteFor("mysql"))
            .withDatabaseName("portfolio")
            .withUsername("afs")
            .withPassword("afs");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TxnRepository txnRepository;

    @Test
    void test1() throws Exception {
        jdbcTemplate.update("INSERT INTO txn(txn_id, type, amount, description) values (1, \"CREDIT\", 1000, \"initial setup\");");
        jdbcTemplate.update("INSERT INTO txn(txn_id, type, amount, description) values (2, 'DEBIT', 500, 'wdl 1');");
        jdbcTemplate.update("INSERT INTO txn(txn_id, type, amount, description) values (3, 'CREDIT', 200, 'top-up');");
   

        List<Txn> results = txnRepository.findByType("CREDIT");
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, 
                "spring.datasource.url=" + database.getJdbcUrl(),
                "spring.datasource.username=" + database.getUsername(),
                "spring.datasource.password=" + database.getPassword());
         }
    }
}
