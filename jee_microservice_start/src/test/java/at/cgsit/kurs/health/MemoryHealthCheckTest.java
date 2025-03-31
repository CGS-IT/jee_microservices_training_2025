package at.cgsit.kurs.health;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class MemoryHealthCheckTest {

    @Test
    void testMemoryHealthLiveness() {
        given()
            .when().get("/q/health/live")
            .then()
            .statusCode(200)
            .body("status", equalTo("UP"))
            .body("checks.name", hasItem("MemoryHealthCheck"))
            .body("checks.find { it.name == 'MemoryHealthCheck' }.status", equalTo("UP"))
            .body("checks.find { it.name == 'MemoryHealthCheck' }.data.usagePercent", lessThanOrEqualTo(100));
    }

    @Test
    void testMemoryHealthReadiness() {
        given()
            .when().get("/q/health/ready")
            .then()
            .statusCode(200)
            .body("status", equalTo("UP"))
            .body("checks.name", hasItem("MemoryHealthCheck"))
            .body("checks.find { it.name == 'MemoryHealthCheck' }.status", equalTo("UP"))
            .body("checks.find { it.name == 'MemoryHealthCheck' }.data.status", equalTo("OK"));
    }
}
