package at.cgst.kurs.repository;

import at.cgst.kurs.model.TestEntity;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TestEntityRepositoryTest {

  @Inject
  TestEntityRepository testEntityRepository;

  @Test
  public void testInsertTestEntity() {
    TestEntity testEntity = new TestEntity();
    testEntity.setName("name");

    TestEntity testEntity1 = testEntityRepository.insertTestEntity(testEntity);

    assertNotNull(testEntity1);

  }

}