package com.afsheen.portfoliotracker.controller;

import com.afsheen.portfoliotracker.dto.TxnRequestBody;
import com.afsheen.portfoliotracker.repository.TxnRepository;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
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

import java.math.BigDecimal;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.*;

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
        Response response = invoke_findAll();

        assertNotNull(response);
    }

    @Test
    void testCreate() {
        TxnRequestBody req = new TxnRequestBody();
        req.setType("CREDIT");
        req.setAmount(BigDecimal.TEN);
        req.setDescription("test081");

        Response response = invoke_create(req);
        assertNotNull(response);

        assertEquals(201, response.statusCode());
    }

    private Response invoke_findAll() {
        return when()
                .request("GET", "/txns")
                .then()
                .log()
                .ifValidationFails(LogDetail.BODY)
                .extract()
                .response();
    }

    private Response invoke_create(TxnRequestBody txnRequestBody) {
        return with()
                .body(txnRequestBody)
                .when()
                .contentType(ContentType.JSON)
                .request("POST", "/txns")
                .then()
                .log()
                .ifValidationFails(LogDetail.BODY)
                .extract()
                .response();
    }

}
