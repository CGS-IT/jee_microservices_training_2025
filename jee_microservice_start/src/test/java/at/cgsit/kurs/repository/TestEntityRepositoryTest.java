package at.cgsit.kurs.repository;

import at.cgsit.kurs.base.BaseQuarkusTest;
import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.testutil.TestDefaults;
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
class TestEntityRepositoryTest extends BaseQuarkusTest {

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
        .anyMatch(e -> TestNames.HERR_MANN.getName().equals(e.getName())));
  }

  @Test
  void testChrisAndFrankWithAssert() {
    List<String> names = repository.findAll().stream()
        .map(TestEntity::getName)
        .toList();

    assertTrue(names.contains(TestNames.HERR_MANN.getName()), "Should contain Chris");
    assertTrue(names.contains(TestNames.FRANK_ELSTNER.getName()), "Should contain Frank");
  }

  /**
   * Test findByName with exact match
   */
  @Test
  void testFindByNameExact() {
    List<TestEntity> result = repository.findByName(TestNames.HERR_MANN.getName() , true);
    assertEquals(1, result.size());
    assertEquals(TestNames.HERR_MANN.getName(), result.getFirst().getName());
  }

  /**
   * find exact by name but with Cirteria API
   */
  @Test
  void findByNameCriteriaApiExact() {
    List<TestEntity> all = repository.findAll();
    List<TestEntity> result = repository.findByNameCriteriaApi(TestNames.HERR_MANN.getName() , true);
    assertEquals(1, result.size());
    assertEquals(TestNames.HERR_MANN.getName(), result.getFirst().getName());
  }

  /**
   * Test findByName with LIKE match
   */
  @Test
  void testFindByNameLike() {
    List<TestEntity> result = repository.findByName(TestDefaults.MACH_ELSTNER_PARTIAL, false);
    assertFalse(result.isEmpty());
    assertTrue(result.stream().anyMatch(e -> e.getName().contains(TestDefaults.MACH_ELSTNER_PARTIAL)));
  }

  /**
   * Test findByName with exact match and no result
   */
  @Test
  void testFindByNameExact_NoMatch() {
    String nonExistentName = TestDefaults.NON_EXISTING_NAME;

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
    TestEntity testEntity = new TestEntity(TestNames.INSERTED.getVorname(), TestNames.INSERTED.getName());

    TestEntity persisted = repository.insertTestEntity(testEntity);

    assertNotNull(persisted);
    assertNotNull(persisted.getId());
    assertTrue(repository.findAll().stream()
        .anyMatch(e -> TestNames.INSERTED.getName().equals(e.getName())));
  }

  /**
   * Test update of a TestEntity
   */
  @Test
  @TestTransaction
  void testUpdateTestEntity() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("temp", "temp"));
    entity.setName(TestNames.UPDATED.getName());
    entity.setVorname(TestNames.UPDATED.getVorname());

    TestEntity updated = repository.updateTestEntity(entity);

    assertEquals(TestNames.UPDATED.getName(), updated.getName());
  }

  /**
   * Test deletion of a TestEntity
   */
  @Test
  @TestTransaction
  void testDeleteTestEntity() {
    TestEntity entity = repository.insertTestEntity(new TestEntity(TestNames.TO_DELETE.getName(), TestNames.TO_DELETE.getVorname()));
    repository.deleteTestEntity(entity);

    boolean stillExists = repository.findAll().stream()
        .anyMatch(e -> TestNames.TO_DELETE.getName().equals(e.getName()));

    assertFalse(stillExists, "Entity should have been deleted");
  }

  /**
   * Test findById and readTestEntityById
   */
  @Test
  @TestTransaction
  void testFindByIdAndReadById() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("Findable", "Findable"));
    Long id = entity.getId();

    TestEntity found = repository.findById(id);
    assertNotNull(found);
    assertEquals("Findable", found.getName());

    TestEntity read = repository.readTestEntityById(id);
    assertNotNull(read);
    assertEquals("Findable", read.getName());
  }

  /**
   * Test delete by ID
   */
  @Test
  @TestTransaction
  void testDeleteById() {
    TestEntity entity = repository.insertTestEntity(new TestEntity("ToDeleteById" , "ToDeleteById"));
    repository.deleteTestEntity(entity);
    TestEntity shouldBeGone = repository.findById(entity.getId());
    assertNull(shouldBeGone);
  }

}

