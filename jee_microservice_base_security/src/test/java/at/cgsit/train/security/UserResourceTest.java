package at.cgsit.train.security;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class UserResourceTest {

    @Test
    void testMe_asUserRole() {
        given()
            .auth().preemptive().basic("user", "user")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/users/me")
        .then()
            .statusCode(200)
            .body(is("user"));
    }

    @Test
    void testMe_asAdminRole() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/users/me")
        .then()
            .statusCode(200)
            .body(is("admin"));
    }

    @Test
    void testMe_asBlockedRole_forbidden() {
        // Assuming blocked user exists with username "blocked_user" and password "blocked"
        Response response = given()
            .auth().preemptive().basic("blocked_user", "blocked")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/users/me");

        assertEquals(403, response.getStatusCode());
        // we would not recommend this as it is not a good practice to expose the error message to the client
        // assertEquals("Access denied for role: blocked", response.getBody().asString());
    }

    @Test
    void testMe_unauthenticated() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/api/users/me")
        .then()
            .statusCode(401); // unauthorized
    }
}
