package at.cgsit.kurs.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@TestHTTPEndpoint(ConfigPropertyResource.class)
class ConfigPropertyResourceTest {

    @Test
    void testShowMessage_shouldReturnMessageFromConfig() {
        given()
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.TEXT)
            .body(containsString("Hello:"));
    }

    @Test
    void testShowMessageFromProvider_shouldReturnMessageFromConfigProvider() {
        given()
        .when()
            .get("/fromProvider")
        .then()
            .statusCode(200)
            .contentType(ContentType.TEXT)
            .body(containsString("Hello: config read via Config Provider"));
    }

    @Test
    void testShowMessageFromProvider_shouldReturnDefaultIfConfigMissing() {
        // Simulate failure/missing value — use `ConfigProvider` in app code to read missing key
        String originalValue = System.getProperty("greeting.message");
        System.clearProperty("greeting.message");

        given()
        .when()
            .get("/fromProvider")
        .then()
            .statusCode(200)
            .body(is(notNullValue()));

        if (originalValue != null) {
            System.setProperty("greeting.message", originalValue);
        }
    }

    @Test
    void testInvalidPath_shouldReturn404() {
        given()
        .when()
            .get("/invalid-path")
        .then()
            .statusCode(404);
    }
}
