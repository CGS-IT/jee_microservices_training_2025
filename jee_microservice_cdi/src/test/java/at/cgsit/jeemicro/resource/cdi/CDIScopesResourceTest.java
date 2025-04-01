package at.cgsit.jeemicro.resource.cdi;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class CDIScopesResourceTest {

    @Test
    void testRequestScope() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/simplecdi_scopes/requestScope")
        .then()
            .statusCode(200)
            .body(is("Kontonummer zum Buchen: 1"));
    }

    @Test
    void testApplicationScope_incrementedCounter() {
        Integer first = Integer.valueOf(
            given()
                .accept(ContentType.TEXT)
            .when()
                .get("/simplecdi_scopes/applicationScope")
            .then()
                .statusCode(200)
                .extract().asString()
        );

        Integer second = Integer.valueOf(
            given()
                .accept(ContentType.TEXT)
            .when()
                .get("/simplecdi_scopes/applicationScope")
            .then()
                .statusCode(200)
                .extract().asString()
        );

        assert second > first;
    }
}
