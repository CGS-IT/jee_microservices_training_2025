package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.TestEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the {@link TestEntityRepository}.
 * <p>
 * This test class demonstrates how to:
 * <ul>
 *   <li>Set up shared test data using {@code @BeforeAll} with {@code @Transactional}</li>
 *   <li>Use {@code @TestTransaction} to test insert, update, and delete operations with rollback</li>
 *   <li>Validate data integrity using JUnit 5 assertions</li>
 *   <li>Organize and reuse test constants using an enum ({@link TestNames})</li>
 * </ul>
 * <p>
 * The class uses {@code @QuarkusTest} to run tests inside the Quarkus application context with an in-memory
 * database (typically H2 during test runtime).
 * <p>
 * Tests are isolated and transactional, ensuring database state is consistent and clean after each test.
 * <p>
 * This example is useful for:
 * <ul>
 *   <li>Developers learning how to write Quarkus-based repository tests</li>
 *   <li>Understanding how to perform CRUD operation tests in a transactional and rollback-safe manner</li>
 *   <li>Applying best practices like constants management, test readability, and structured setup</li>
 * </ul>
 *
 * @author CGS-IT Solutions GmbH
 */
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestEntityRepositoryTest {

  enum TestNames {
    CHRIS("Chris"),
    FRANK("Frank"),
    INSERTED("insertedName"),
    UPDATED("updatedName"),
    TO_DELETE("toDelete");

    final String value;

    TestNames(String value) {
      this.value = value;
    }
  }

  @Inject
  TestEntityRepository repository;

  @BeforeAll
  @Transactional
  void initData() {
    // dbunit.cleanINsert("/data/testentity.xml");
    repository.deleteAll();
    repository.insertTestEntity(new TestEntity(TestNames.CHRIS.value));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK.value));
  }

  /**
   * Test count of TestEntities
   */
  @Test
  void testCountTestEntities() {
    long count = repository.countTestEntities();
    assertTrue(count >= 2, "Expected at least two pre-initialized entities");
  }

  @Test
  void testChris() {
    assertTrue(repository.findAll().stream()
        .anyMatch(e -> TestNames.CHRIS.value.equals(e.getName())));
  }

  @Test
  void testChrisAndFrankWithAssert() {
    List<String> names = repository.findAll().stream()
        .map(TestEntity::getName)
        .toList();

    assertTrue(names.contains(TestNames.CHRIS.value), "Should contain Chris");
    assertTrue(names.contains(TestNames.FRANK.value), "Should contain Frank");
  }

  /**
   * Test findByName with exact match
   */
  @Test
  void testFindByNameExact() {
    List<TestEntity> result = repository.findByName(TestNames.CHRIS.value, true);
    assertEquals(1, result.size());
    assertEquals(TestNames.CHRIS.value, result.get(0).getName());
  }

  /**
   * find exact by name but with Cirteria API
   */
  @Test
  void findByNameCriteriaApiExact() {
    List<TestEntity> result = repository.findByNameCriteriaApi(TestNames.CHRIS.value , true);
    assertEquals(1, result.size());
    assertEquals(TestNames.CHRIS.value, result.get(0).getName());
  }

  /**
   * Test findByName with LIKE match
   */
  @Test
  void testFindByNameLike() {
    List<TestEntity> result = repository.findByName("ris", false);
    assertFalse(result.isEmpty());
    assertTrue(result.stream().anyMatch(e -> e.getName().contains("ris")));
  }

  /**
   * Test findByName with exact match and no result
   */
  @Test
  void testFindByNameExact_NoMatch() {
    String nonExistentName = "NonExistingName";

    List<TestEntity> result = repository.findByName(nonExistentName, true);

    assertNotNull(result, "Result list should not be null");
    assertTrue(result.isEmpty(), "Expected no entities with name: " + nonExistentName);
  }

  /**
   * Test insert of a new TestEntity
   * Transaction will rollback after test due to @TestTransaction
   */
  @Test
  @TestTransaction
  void testInsertTestEntity() {
    TestEntity testEntity = new TestEntity(TestNames.INSERTED.value);

    TestEntity persisted = repository.insertTestEntity(testEntity);

    assertNotNull(persisted);
    assertNotNull(persisted.getId());
    assertTrue(repository.findAll().stream()
        .anyMatch(e -> TestNames.INSERTED.value.equals(e.getName())));
  }

  /**
   * Test update of a TestEntity
   */
  @Test
  @TestTransaction
  void testUpdateTestEntity() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("temp"));
    entity.setName(TestNames.UPDATED.value);

    TestEntity updated = repository.updateTestEntity(entity);

    assertEquals(TestNames.UPDATED.value, updated.getName());
  }

  /**
   * Test deletion of a TestEntity
   */
  @Test
  @TestTransaction
  void testDeleteTestEntity() {
    TestEntity entity = repository.insertTestEntity(new TestEntity(TestNames.TO_DELETE.value));
    repository.deleteTestEntity(entity);

    boolean stillExists = repository.findAll().stream()
        .anyMatch(e -> TestNames.TO_DELETE.value.equals(e.getName()));

    assertFalse(stillExists, "Entity should have been deleted");
  }

  /**
   * Test findById and readTestEntityById
   */
  @Test
  @TestTransaction
  void testFindByIdAndReadById() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("Findable"));
    Long id = entity.getId().longValue();

    TestEntity found = repository.findById(id);
    assertNotNull(found);
    assertEquals("Findable", found.getName());

    TestEntity read = repository.readTestEntityById(id.intValue());
    assertNotNull(read);
    assertEquals("Findable", read.getName());
  }

  /**
   * Test delete by ID
   */
  @Test
  @TestTransaction
  void testDeleteById() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("ToDeleteById"));
    repository.deleteTestEntity(entity);
    TestEntity shouldBeGone = repository.findById(entity.getId().longValue());
    assertNull(shouldBeGone);
  }

}

