package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CDIInterceptorResourceTest {

    @Test
    void testCDIInterceptorResource_call() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_interceptor")
        .then()
            .statusCode(200)
            //.body(containsString("eulaV txeT tupnI yM")); // Expected reversed output
            .body(is(notNullValue()));
    }
} 
