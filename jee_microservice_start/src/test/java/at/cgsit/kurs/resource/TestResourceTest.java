package at.cgsit.kurs.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import at.cgsit.kurs.dto.TestDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class TestResourceTest {

    @Test
    public void testReadObjectById_ValidInput() {
      given()
          .pathParam("id", 1)
          .when()
          .get("/test/{id}")
          .then()
          .statusCode(200)
          .contentType(ContentType.JSON);
          //.body("id", equalTo(1))
          //.body("name", equalTo("resultName"))
          //.body("vorname", equalTo("resultName"))
          //.body("eventDate", notNullValue());
    }

    @Test
    public void testReadObjectById_InvalidInput() {
      given()
          .pathParam("id", "abc")
          .when()
          .get("/test/{id}")
          .then()
          .statusCode(404); // or 400 if you add proper exception mapping
    }

  @Test
  public void testReadObjectById_Deserialization() {
    TestDTO dto = given()
        .pathParam("id", 1)
        .when()
        .get("/test/{id}")
        .then()
        .statusCode(200)
        .extract()
        .as(TestDTO.class);

    assertNotNull(dto);
    assertEquals(1L, dto.getId());
    assertNotNull( dto.getName());
    // assertEquals("resultName", dto.getVorname());
    assertNotNull(dto.getVorname());
    // assertNotNull(dto.getEventDate());
  }


  }
