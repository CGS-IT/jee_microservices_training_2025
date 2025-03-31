package at.cgsit.jeemicro.resource;

import at.cgsit.jeemicro.ExampleResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

//

@QuarkusTest
public class ExampleResourceTest {

  @Test
  public void testHelloEndpoint() {
    given()
        .when()
        .get("/api/hello")
        // .get()
        .then()
        .statusCode(200)
        .body(is(notNullValue()));
  }
}