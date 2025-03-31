package at.cgsit.kurs.resource;

import at.cgsit.kurs.model.ErrorEntity;
import at.cgsit.kurs.repository.ErrorEntityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ErrorEntityResourceTest {

    @Inject
    ErrorEntityRepository repository;

    @BeforeAll
    @Transactional
    void insertInitialEntity() {
        repository.save(new ErrorEntity("initial value", "unique123"));
    }

    @Test
    void testNotNullViolationReturns400() {
        given()
            .contentType("application/json")
            .body("""
                {
                    "uniqueValue": "something"
                }
                """)
            .when()
            .post("/error-entity")
            .then()
            .statusCode(400)
            .contentType("application/json")
            .body("message", equalTo("A database error occurred. Please contact support."))
            .body("error", equalTo("ConstraintViolationException"));
    }

    @Test
    void testUniqueConstraintViolationReturns400() {
        given()
            .contentType("application/json")
            .body("""
                {
                    "notNullValue": "another value",
                    "uniqueValue": "unique123"
                }
                """)
            .when()
            .post("/error-entity")
            .then()
            .statusCode(400)
            .contentType("application/json")
            .body("message", equalTo("A database error occurred. Please contact support."))
            .body("error", equalTo("ConstraintViolationException"));
    }
}
