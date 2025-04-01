package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CDIEventsResourceTest {

    @Test
    void testCDIEventsResource_call() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_events")
        .then()
            .statusCode(200)
            .body(is(""));
    }
} 
