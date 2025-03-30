package at.cgsit.kurs.resource;

import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *       // .get("/test/{id}")
 *       // using Test Resource http endpoint .. all tests are RELATIVE to the TestResource.class
 */
@QuarkusTest
@TestHTTPEndpoint(TestResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestResourceUpdatesTest {

  @Inject
  TestEntityRepository repository;

  @BeforeEach
  @Transactional
  void initData() {
    // dbunit.cleanINsert("/data/testentity.xml");
    repository.deleteAll();
    repository.resetIdSequence(); // hack .. you should not do this in real tests, use dbuint including ids for example
    repository.insertTestEntity(new TestEntity(TestNames.HERR_MANN.getVorname(), TestNames.HERR_MANN.getName()));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK_ELSTNER.getVorname(), TestNames.FRANK_ELSTNER.getName()));
  }

  @Test
  @Transactional
  public void testUpdateTestEntity_valid() {

    TestEntity first = repository.findAll().getFirst();
    assertNotNull(first);
    String originalName = first.getName();


    Map<String, Object> dto = new HashMap<>();
    dto.put("id", first.getId());
    dto.put("name", "Updated Name");

    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .pathParam("id", first.getId())
        .when()
        .put("/{id}")
        .then()
        .statusCode(200)
        .body("name", equalTo("Updated Name"));

    TestEntity testEntity = repository.readTestEntityById(first.getId());
    testEntity.setName(originalName);
    repository.updateTestEntity(testEntity);

  }

  @Test
  public void testUpdateTestEntity_NotFound() {
    Map<String, Object> dto = new HashMap<>();
    dto.put("name", "Ghost");

    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .pathParam("id", 9999)
        .when()
        .put("/{id}")
        .then()
        .statusCode(404)
        .body(containsString("Entity not found"));
  }

  @Test
  public void testUpdateTestEntity_InvalidInput() {
    Map<String, Object> dto = new HashMap<>();
    dto.put("name", ""); // invalid

    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .pathParam("id", 1)
        .when()
        .put("/{id}")
        .then()
        .statusCode(400)
        .body(containsString("Name field cannot be empty"));
  }

  // helper method to create a test entity for deletion
  private Long createTestEntityForDeletion() {
    Map<String, Object> dto = new HashMap<>();
    dto.put("name", "ToDelete");
    dto.put("vorname", "Test");

    return given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .jsonPath()
        .getLong("id");
  }

}
