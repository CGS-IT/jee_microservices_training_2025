package at.cgsit.train.security;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class PublicResourceTest {

    @Test
    void testPublicResource_accessWithoutAuthentication() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/api/public")
        .then()
            .statusCode(200)
            .body(is("public"));
    }

    @Test
    void testPublicResource_accessWithAuthentication() {
        given()
            .auth().preemptive().basic("user", "user")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/public")
        .then()
            .statusCode(200)
            .body(is("public"));
    }
} 
