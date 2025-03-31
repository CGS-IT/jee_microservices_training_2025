package at.cgsit.kurs.repository;

import at.cgsit.kurs.base.BaseQuarkusTest;
import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Panache_TestEntityRepositoryTest extends BaseQuarkusTest {

  @Inject
  Panache_TestEntityRepository repository;

  @Test
  void testFindAllPaged() {
    PanacheQuery<TestEntity> allPaged = repository.findAllPaged(null);
    assertEquals(2, allPaged.stream().count());
    TestEntity testEntity = allPaged.firstResult();
    assertEquals(TestNames.HERR_MANN.getName(), testEntity.getName());
  }

  @Test
  void testFindAllPagedMann() {
    PanacheQuery<TestEntity> allPaged = repository.findAllPaged(TestNames.HERR_MANN.getName());
    assertEquals(1, allPaged.stream().count());
    TestEntity testEntity = allPaged.firstResult();
    assertEquals(TestNames.HERR_MANN.getName(), testEntity.getName());
  }


  @Test
  void countTestEntities() {
    long l = repository.countTestEntities();
    assertEquals(2, l);
  }
}