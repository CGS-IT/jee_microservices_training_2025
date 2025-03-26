package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.ChildEntity;
import at.cgsit.kurs.model.TestEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
    // repository.deleteTestEntity();
    repository.insertTestEntity(new TestEntity(TestNames.CHRIS.value));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK.value));
  }


  /**
   * add a child entity to a parent entity
   * and check if the child is in the list
   */
  @Test
  @TestTransaction
  void testInsertChildEntity() {

    List<TestEntity> resultList = repository.findByName(TestNames.FRANK.value);
    TestEntity frank = resultList.get(0);

    ChildEntity childEntity = new ChildEntity("child1", frank);

    repository.updateTestEntity(frank);

    // check if child is in the list
    List<TestEntity> byName = repository.findByName(frank.getName());

    assertEquals(1, byName.size());
    TestEntity frankFound = resultList.get(0);
    int size = frankFound.getChildren().size();
    assertEquals(1, size);
    assertEquals("child1", frankFound.getChildren().get(0).getChildName());

    // eager load the children with joined query
    TestEntity byIdWithChildren = repository.findByIdWithChildren(frankFound.getId());

    assertNull(byIdWithChildren);

  }




}

