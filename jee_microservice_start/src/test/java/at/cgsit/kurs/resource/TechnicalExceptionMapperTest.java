package at.cgsit.kurs.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class TechnicalExceptionMapperTest {

    @Test
    void testGenericExceptionMappedTo500() {
        given()
          .when().get("/test/error/runtime")
          .then()
            .statusCode(500)
            .contentType("application/json")
            .body("message", equalTo("A technical error occurred. Please contact support."))
            .body("error", equalTo("RuntimeException"))
            .body("timestamp", notNullValue());
            //.body("timestamp", matchesRegex(
            //    "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?(Z|[+-]\\d{2}:\\d{2})$"
            //));
    }

    @Test
    void testNotFoundExceptionReturns404() {
        given()
          .when().get("/test/error/notfound")
          .then()
            .statusCode(404);
    }
}
