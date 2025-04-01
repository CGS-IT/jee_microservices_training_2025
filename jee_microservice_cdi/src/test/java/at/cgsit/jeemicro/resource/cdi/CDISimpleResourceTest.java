package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CDISimpleResourceTest {

    @Test
    void testCDISimpleResource_call() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi")
        .then()
            .statusCode(200)
            .body(is( notNullValue() ) );
    }
} 
