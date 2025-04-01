package at.cgsit.kurs.translator;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.testutil.TestDataFactory;
import at.cgsit.kurs.testutil.TestDefaults;
import io.quarkus.test.component.QuarkusComponentTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
class TestEntityTranslatorQuarkusTest {

    @Inject
    protected TestEntityTranslator translator;

    // --- toDTO tests ---

    @Test
    void testToDTO_withValidEntity() {
        TestEntity entity = TestDataFactory.defaultEntity();
        TestDTO dto = translator.toDTO(entity);
        TestDataFactory.assertFullDTO(dto, TestDefaults.VALID_ID, TestDefaults.VALID_VERSION, TestDefaults.VALID_NAME);
    }

    @Test
    void testToDTO_withNullEntity_returnsNull() {
        assertNull(translator.toDTO(null));
    }

    @Test
    void testToDTO_withNullId() {
        TestEntity entity = TestDataFactory.defaultEntity();
        entity.setId(null);

        TestDTO dto = translator.toDTO(entity);
        assertNull(dto.getId());
        assertEquals(TestDefaults.VALID_VERSION, dto.getVersionNumber());
        assertEquals(TestDefaults.VALID_NAME, dto.getName());
    }

    @Test
    void testToDTO_withNullVersionAndName() {
        TestEntity entity = TestDataFactory.defaultEntity();
        entity.setVersionNo(null);
        entity.setName(null);

        TestDTO dto = translator.toDTO(entity);
        assertEquals(TestDefaults.VALID_ID, dto.getId());
        assertNull(dto.getVersionNumber());
        assertNull(dto.getName());
    }

    // --- toEntity tests ---

    @Test
    void testToEntity_withValidDTO() {
        TestDTO dto = TestDataFactory.defaultDTO();
        TestEntity entity = translator.toEntity(dto);
        TestDataFactory.assertFullEntity(entity, TestDefaults.VALID_ID, TestDefaults.VALID_VERSION, TestDefaults.VALID_NAME);
    }

    @Test
    void testToEntity_withNullDTO_returnsNull() {
        assertNull(translator.toEntity(null));
    }

    @Test
    void testToEntity_withNullId() {
        TestDTO dto = TestDataFactory.defaultDTO();
        dto.setId(null);

        TestEntity entity = translator.toEntity(dto);
        assertNull(entity.getId());
        assertEquals(TestDefaults.VALID_VERSION, entity.getVersionNo());
        assertEquals(TestDefaults.VALID_NAME, entity.getName());
    }

    @Test
    void testToEntity_withNullVersionAndName() {
        TestDTO dto = TestDataFactory.defaultDTO();
        dto.setVersionNumber(null);
        dto.setName(null);

        TestEntity entity = translator.toEntity(dto);
        assertEquals(TestDefaults.VALID_ID, entity.getId());
        assertNull(entity.getVersionNo());
        assertNull(entity.getName());
    }


}
