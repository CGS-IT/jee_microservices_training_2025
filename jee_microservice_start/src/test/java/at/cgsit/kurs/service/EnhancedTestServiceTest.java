package at.cgsit.kurs.service;

import at.cgsit.kurs.base.BaseQuarkusTest;
import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
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

  @Test
  void testCreate_validDto() {
    TestDTO dto = new TestDTO();
    dto.setName("NewName");
    dto.setVersionNumber(2L);
    dto.setVorname("TestVorname");
    dto.setActive(true);

    // creates with dto in , returns dto out
    TestDTO testDTO = service.create(dto);
    assertNotNull(testDTO.getId());
  }

  @Test
  void testCreate_invalidDto_shouldFailValidation() {
    TestDTO dto = new TestDTO();
    dto.setName(null); // missing required name

    assertThrows(Exception.class, () -> service.create(dto));
  }


}