package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class CDIProducerResourceTest {

    @Test
    void testCDIProducerResource_call() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_producers")
        .then()
            .statusCode(200)
            .body(containsString("CDIProducerResource"));
    }
}