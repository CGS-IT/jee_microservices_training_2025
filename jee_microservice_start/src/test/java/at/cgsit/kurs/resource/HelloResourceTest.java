package at.cgsit.kurs.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
// if you want to test a specific endpoint, use the @TestHTTPEndpoint annotation
// doing so will set the base path in get() to /hello already
// @TestHTTPEndpoint(TestResource.class)
// random order to avoid dependencies between tests, but be sure.
// if you need use order use @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// or implement your own orderer Class by implementing the MethodOrderer interface
@TestMethodOrder(MethodOrderer.Random.class)
class HelloResourceTest {

  @Test
  // @Disabled("Temporarily disabled for debugging")
  void testHelloEndpoint() {
    given()
        .when()
          .get("/hello")
        .then()
          .statusCode(200)
          .body(is(notNullValue()));
  }

  @Test
    // @Disabled("Temporarily disabled for debugging")
  void testHelloSubPathEndpoint() {
    given()
        .when()
          .get("/hello/neu")
        .then()
          .statusCode(200)
          .body(is(notNullValue()));
  }

}