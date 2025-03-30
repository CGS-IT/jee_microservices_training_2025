package at.cgsit.kurs.resource;

import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import at.cgsit.kurs.dto.TestDTO;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
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
class TestResourceTest {

  @Inject
  TestEntityRepository repository;

  @BeforeAll
  @Transactional
  void initData() {
    // dbunit.cleanINsert("/data/testentity.xml");
    repository.deleteAll();
    repository.resetIdSequence(); // hack .. you should not do this in real tests, use dbuint including ids for example
    repository.insertTestEntity(new TestEntity(TestNames.HERR_MANN.getVorname(), TestNames.HERR_MANN.getName()));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK_ELSTNER.getVorname(), TestNames.FRANK_ELSTNER.getName()));
  }


    @Test
    public void testReadTestById_ValidInput() {

      TestEntity first = repository.findAll().getFirst();
      assertNotNull(first);

      // .get("/test/{id}")
      // using Test Resource http endpoint .. all tests are RELATIVE to the TestResource.class
      given()
          .pathParam("id", first.getId())
          .when()
            .get("/{id}")
          .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(first.getId().intValue()))
            .body("name", equalTo(TestNames.HERR_MANN.getName()))
            .body("vorname", equalTo(TestNames.HERR_MANN.getVorname()));

    }

    @Test
    public void testReadTestById_InvalidInput() {
      given()
          .pathParam("id", "abc")
          .when()
          .get("/{id}")
          .then()
          .statusCode(404); // or 400 if you add proper exception mapping
    }

  @Test
  public void testReadTestById_Deserialization() {

    TestEntity first = repository.findAll().getFirst();
    assertNotNull(first);

    TestDTO dto =
        given()
          .pathParam("id", first.getId())
        .when()
          .get("/{id}")
        .then()
          .statusCode(200)
          .extract()
          .as(TestDTO.class);

    assertNotNull(dto);
    assertEquals(first.getId(), dto.getId());
    assertNotNull( dto.getName());
    assertNotNull(dto.getVorname());

  }

  @Test
  public void testCreateTestEntity() {
    Map<String, Object> dto = new HashMap<>();
    dto.put("name", TestNames.INSERTED.getName());
    dto.put("vorname", TestNames.INSERTED.getVorname());

    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
        .post("") // adjust this path to match your endpoint
        .then()
        .log().body()
        .statusCode(201)
        .body("id", greaterThan(0))
        .body("name", equalTo(TestNames.INSERTED.getName())); // Since response DTO sets vorname = ""

      // mögliche nochmals prüfung ob die daten auch in der datenbank angelegt wurden
      // this.testResource.readObjectById(1L);

  }

  @Test
  public void testGetAll() {
    Response response =
        given()
        .queryParam("page", 0)
        .queryParam("size", 100)
        .when()
        .get()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .log().body() // logs the response body
        .extract()
        .response();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertFalse(response.getBody().asString().isEmpty());

  }


  @Test
  public void testGetAllOrByNamePaged() {

    Response response = given()
        .queryParam("name", TestNames.HERR_MANN.getName())
        .queryParam("page", 0)
        .queryParam("size", 5)
        .when()
        .get()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .log().body() // logs the response body
        .extract()
        .response();

    // Deserialize to List<TestDTO>
    List<TestDTO> dtos = response.jsonPath().getList(".", TestDTO.class);

    // Log results one by one
    System.out.println("📦 Received " + dtos.size() + " DTO(s):");
    dtos.forEach(dto -> System.out.println("🧾 " + dto));

    List<TestEntity> all = repository.findAll();
    all.forEach(System.out::println);

  }

  // test invalid query parameters
  // validated with Bean Validation
  // we return 422 Unprocessable Entity
  @Test
  public void testGetAllOrByNamePaged_InvalidParams() {
    given()
        .queryParam("page", -1)
        .queryParam("size", -5)
        .when()
        .get()
        .then()
        .statusCode(422); // if you implement proper validation
  }

  @Test
  public void testCountTestResources() {
    given()
        .when()
        .get("/count")
        .then()
        .statusCode(200)
        .contentType(ContentType.TEXT)
        .body(containsString("Anzahl TestEntities"));
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

  @Test
  public void testQueryParameter_exactMatch() {
    given()
        .queryParam("name", TestNames.HERR_MANN.getName())
        .queryParam("exact", true)
        .when()
        .get("/find")
        .then()
        .statusCode(200)
        .body(containsString("Anzahl TestEntities:"));
  }

  @Test
  public void testDeleteTestEntity_valid() {
    Long newId = createTestEntityForDeletion(); // helper to create temp entity

    given()
        .pathParam("id", newId)
        .when()
        .delete("/{id}")
        .then()
        .statusCode(204);
  }

  @Test
  public void testDeleteTestEntity_notFound() {
    given()
        .pathParam("id", 9999)
        .when()
        .delete("/{id}")
        .then()
        .statusCode(404)
        .body(containsString("Entity not found"));
  }

  @Test
  public void testAddChildToParent_valid() {

    TestEntity first = repository.findAll().getFirst();
    assertNotNull(first);

    Map<String, Object> child = new HashMap<>();
    child.put("child_name", "NewChild");
    child.put("parent_id", first.getId());

    given()
        .contentType(ContentType.JSON)
        .pathParam("parentId", first.getId())
        .body(child)
        .when()
        .post("/{parentId}/child")
        .then()
        .statusCode(201)
        .body(containsString("Child added successfully"));
  }

  @Test
  public void testAddChildToParent_invalidParent() {
    Map<String, Object> child = new HashMap<>();
    child.put("child_name", "GhostChild");
    child.put("parent_id", "9999");

    given()
        .contentType(ContentType.JSON)
        .pathParam("parentId", 9999)
        .body(child)
        .when()
        .post("/{parentId}/child")
        .then()
        .statusCode(404)
        .body(containsString("Parent TestEntity with ID 9999 not found."));
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
