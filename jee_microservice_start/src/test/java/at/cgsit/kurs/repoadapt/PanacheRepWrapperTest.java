package at.cgsit.kurs.repoadapt;

import at.cgsit.kurs.base.BaseQuarkusTest;
import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PanacheRepWrapperTest extends BaseQuarkusTest {

  @Inject
  PanacheRepWrapper wrapper;

  @Test
  void testFindByNamePaged_withName() {
    List<TestEntity> result = wrapper.findByNamePaged(TestNames.FRANK_ELSTNER.getName(), 0, 10);
    assertEquals(1, result.size());
    assertEquals(TestNames.FRANK_ELSTNER.getName(), result.getFirst().getName());
  }

  @Test
  void testFindByNamePaged_withoutName() {
    List<TestEntity> result = wrapper.findByNamePaged(null, 0, 10);
    assertEquals(2, result.size());
  }

  @Test
  void testCountTestEntities() {
    long count = wrapper.countTestEntities();
    assertEquals(2, count);
  }

  @Test
  void testFindById() {
    List<TestEntity> resultList = repository.findByName(TestNames.FRANK_ELSTNER.getName());
    TestEntity frank = resultList.getFirst();

    Optional<TestEntity> result = wrapper.findById(frank.getId());
    assertTrue(result.isPresent());
    assertEquals(frank.getName(), result.get().getName());
  }

  @Test
  @TestTransaction
  void testSave() {
    TestEntity entity = new TestEntity();
    entity.setName("New");
    entity.setVersionNo(2L);

    TestEntity saved = wrapper.save(entity);
    assertNotNull(saved.getId());

    TestEntity persisted = repository.findById(saved.getId());
    assertEquals("New", persisted.getName());
  }

  @Test
  @TestTransaction
  void testDeleteById() {
    List<TestEntity> resultList = repository.findByName(TestNames.FRANK_ELSTNER.getName());
    TestEntity frank = resultList.getFirst();

    wrapper.deleteById(frank.getId());
  }
}