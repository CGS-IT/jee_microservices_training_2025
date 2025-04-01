package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class CDIRequestScopedProducerResourceTest {

    @Test
    void testCDIRequestScopedProducerResource_headerA() {
        given()
            .header("X-Bean-Type", "a")
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_request_scoped_producers")
        .then()
            .statusCode(200)
            .body(containsString("CDIRequestScopedProducerResource"));
    }

    @Test
    void testCDIRequestScopedProducerResource_headerB() {
        given()
            .header("X-Bean-Type", "b")
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_request_scoped_producers")
        .then()
            .statusCode(200)
            .body(containsString("CDIRequestScopedProducerResource"));
    }

    @Test
    void testCDIRequestScopedProducerResource_missingHeader_shouldReturn400() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_request_scoped_producers")
        .then()
            .statusCode(400); // Bean Validation on @NotBlank should enforce this
    }
} 
