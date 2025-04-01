package at.cgsit.train.security;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class AdminResourceTest {

    @Test
    void testAdminResource_withAdminRole() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/admin")
        .then()
            .statusCode(200)
            .body(is("admin"));
    }

    @Test
    void testAdminResource_withUserRole_forbidden() {
        Response response = given()
            .auth().preemptive().basic("user", "user")
            .accept(ContentType.TEXT)
        .when()
            .get("/api/admin");

        assertEquals(403, response.getStatusCode());
    }

    @Test
    void testAdminResource_unauthenticated() {
        given()
            .accept(ContentType.TEXT)
        .when()
            .get("/api/admin")
        .then()
            .statusCode(401); // unauthorized
    }
} 
