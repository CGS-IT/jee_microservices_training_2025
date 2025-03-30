package at.cgsit.kurs.repository;

import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.ChildEntity;
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
 *
 * @author CGS-IT Solutions GmbH
 */
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestEntityAndChildRepositoryTest {

  @Inject
  TestEntityRepository repository;

  @BeforeAll
  @Transactional
  void initData() {
    // dbunit.cleanINsert("/data/testentity.xml");
    // we delete the test entities here, because our startup Observer will insert or default data already
    // and we need to guarantee that the data is in the same state for each test
    repository.deleteAll();
    repository.insertTestEntity(new TestEntity(TestNames.HERR_MANN.getName(), TestNames.HERR_MANN.getVorname()));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK_ELSTNER.getName(), TestNames.FRANK_ELSTNER.getVorname()));
  }
  @AfterAll
  void afterAll() {
    // dbunit.cleanINsert("/data/testentity.xml");
  }

  @BeforeEach
  void setUpBeforeEachTestMehtod() {
    // dbunit.cleanINsert("/data/testentity.xml");
  }

  @AfterEach
  void tearDownAfterEachTestMethod() {
    // dbunit.cleanINsert("/data/testentity.xml");
  }

  /**
   * add a child entity to a parent entity
   * and check if the child is in the list
   */
  @Test
  @TestTransaction
  void testInsertChildEntity() {

    List<TestEntity> resultList = repository.findByName(TestNames.FRANK_ELSTNER.getName());
    TestEntity frank = resultList.getFirst();

    ChildEntity childEntity = new ChildEntity(TestDefaults.CHILD_NAME, frank);

    repository.updateTestEntity(frank);

    // check if child is in the list
    List<TestEntity> byName = repository.findByName(frank.getName());
    assertEquals(1, byName.size());

    TestEntity frankFound = resultList.getFirst();
    int size = frankFound.getChildren().size();
    assertEquals(1, size);
    assertEquals(TestDefaults.CHILD_NAME, frankFound.getChildren().getFirst().getChildName());

    // eager load the children with joined query
    TestEntity byIdWithChildren = repository.findByIdWithChildren(frankFound.getId());

    assertNotNull(byIdWithChildren);

  }




}

