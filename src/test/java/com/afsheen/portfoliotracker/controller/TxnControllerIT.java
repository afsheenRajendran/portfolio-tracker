package com.afsheen.portfoliotracker.controller;

import com.afsheen.portfoliotracker.repository.TxnRepository;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing with TestRestTemplate and @Testcontainers (image mysql:8.0-debian)
 */
@IfProfileValue(name="spring.profiles.active", value="integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// activate automatic startup and stop of containers
@Testcontainers
// JPA drop and create table, good for testing
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class TxnControllerIT {

    // static, all tests share this mysql container
    @Container
    @ServiceConnection
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
        DockerImageName.parse("mysql:8.0.36")
                .asCompatibleSubstituteFor("mysql"))
            .withDatabaseName("portfolio")
            .withUsername("afsdb")
            .withPassword("afsdb");

    @LocalServerPort
    int port;

    @Autowired
    private TxnController txnController;

    @Autowired
    private TxnRepository txnRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        txnRepository.deleteAll();
    }

    @Test
    void testSetup() {
        assertTrue(mySQLContainer.isRunning());
    }

    @Test
    void testFindAll() {
        Response response = when()
                .request("GET", "/txns")
                .then()
                .log()
                .ifValidationFails(LogDetail.BODY)
                .extract()
                .response();

        assertNotNull(response);
    }
}
