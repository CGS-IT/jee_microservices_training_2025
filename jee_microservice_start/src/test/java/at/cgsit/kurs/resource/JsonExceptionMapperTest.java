package at.cgsit.kurs.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class JsonExceptionMapperTest {

    @Test
    void testInvalidJsonFormatReturns422() {
        String invalidJson = """
            {
              "id": "not-a-number"
              "name": "Alice",
            }
            """;

        given()
            .body(invalidJson)
            .contentType("application/json")
            .when().post("/test")
            .then()
            .statusCode(422)
            .contentType("application/json")
            .body("message", startsWith("Invalid JSON format"))
            .body("error", equalTo("InvalidFormatException"))
            .body("timestamp", notNullValue());
    }
}
