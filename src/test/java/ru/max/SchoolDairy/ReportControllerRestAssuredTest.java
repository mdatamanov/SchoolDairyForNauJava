package ru.max.SchoolDairy;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReportControllerRestAssuredTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/reports";
    }

    @Test
    void createReport_ShouldReturnStatus200() {
        given()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    void createReport_ShouldReturnReportId() {
        given()
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("reportId", notNullValue());
    }

    @Test
    void getReport_WithValidId_ShouldReturn200() {
        Integer reportId = given()
                .when()
                .post()
                .then()
                .extract()
                .path("reportId");

        given()
                .when()
                .get("/{id}", reportId)
                .then()
                .statusCode(200);
    }

    @Test
    void getReport_WithInvalidId_ShouldReturn404() {
        given()
                .when()
                .get("/99999")
                .then()
                .statusCode(404);
    }

    @Test
    void getReport_WithNegativeId_ShouldReturn400() {
        given()
                .when()
                .get("/-1")
                .then()
                .statusCode(400);
    }

    @Test
    void getReport_WithZeroId_ShouldReturn404() {
        given()
                .when()
                .get("/0")
                .then()
                .statusCode(404);
    }

    @Test
    void getReport_WithStringId_ShouldReturn400() {
        given()
                .when()
                .get("/abc")
                .then()
                .statusCode(400);
    }
}
