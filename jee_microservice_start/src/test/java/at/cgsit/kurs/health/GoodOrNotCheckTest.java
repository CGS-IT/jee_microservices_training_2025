package at.cgsit.kurs.health;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class GoodOrNotCheckTest {

    @Test
    void testHealthEndpoint_whenGoodIsTrue() {
        given()
            .accept(MediaType.APPLICATION_JSON)
        .when()
            .get("/q/health/live")
        .then()
            .statusCode(200)
            .body("checks[0].name", equalTo("GoodOrNotCheck"))
            .body("checks[0].status", equalTo("UP"))
            .body("checks[0].data.reason", containsString("true"));
    }

    // To test the 'false' scenario, override the config dynamically
    @Test
    void testHealthEndpoint_whenGoodIsFalse() {
        System.setProperty("at.cgs.training.goodorNot", "false");

        given()
            .accept(MediaType.APPLICATION_JSON)
        .when()
            .get("/q/health/live")
        .then()
            .statusCode(200)
            .body("checks[0].name", equalTo("GoodOrNotCheck"))
            .body("checks[0].status", equalTo("DOWN"))
            .body("checks[0].data.reason", containsString("false"));

        // Reset for other tests
        System.setProperty("at.cgs.training.goodorNot", "true");
    }
} 
