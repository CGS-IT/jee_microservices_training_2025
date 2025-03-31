package at.cgsit.kurs.service;

import at.cgsit.kurs.base.BaseQuarkusTest;
import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.dto.TestDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnhancedTestServiceTest extends BaseQuarkusTest {

  @Inject
  EnhancedTestService service;

  @org.junit.jupiter.api.Test
  void testEnhancedService() {
    List<TestDTO> byNamePaged = service.findByNamePaged(TestNames.HERR_MANN.getName(), 0, 10);
    assertNotNull(byNamePaged);
    assertEquals(1, byNamePaged.size());
    assertEquals(TestNames.HERR_MANN.getName(), byNamePaged.getFirst().getName());
  }

}