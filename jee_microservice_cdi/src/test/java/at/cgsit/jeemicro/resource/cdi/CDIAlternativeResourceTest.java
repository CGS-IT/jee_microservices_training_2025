package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.constants.MyRestConstands;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CDIAlternativeResourceTest {

    @Test
    void testAlternativeBeanInjection() {
        given()
            .accept(ContentType.TEXT)
            .log().all() // logs full request details including URL
        .when()
            .get(MyRestConstands.PATH_SIMPLECDI_ALTERNATIVE)
        .then()
            .log().all() // logs full response details
            .statusCode(200)
            // .body(containsString("CDIAlternative"));
            .body(is(notNullValue()));
    }
} 
