package at.cgsit.kurs.testutil;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFactory {

    public static TestEntity defaultEntity() {
        TestEntity entity = new TestEntity();
        entity.setId(TestDefaults.VALID_ID);
        entity.setVersionNo(TestDefaults.VALID_VERSION);
        entity.setName(TestDefaults.VALID_NAME);
        entity.setVorname(TestDefaults.DEFAULT_VORNAME);
        entity.setActive(true);
        entity.setEventDate(TestDefaults.FIXED_DATE);
        return entity;
    }

    public static TestDTO defaultDTO() {
        TestDTO dto = new TestDTO();
        dto.setId(TestDefaults.VALID_ID);
        dto.setVersionNumber(TestDefaults.VALID_VERSION);
        dto.setName(TestDefaults.VALID_NAME);
        dto.setVorname(TestDefaults.DEFAULT_VORNAME);
        dto.setActive(true);
        dto.setEventDate(TestDefaults.FIXED_DATE);
        return dto;
    }

    public static void assertFullDTO(TestDTO dto, Long expectedId, Long expectedVersion, String expectedName) {
        assertNotNull(dto);
        assertEquals(expectedId, dto.getId());
        assertEquals(expectedVersion, dto.getVersionNumber());
        assertEquals(expectedName, dto.getName());
        assertEquals(TestDefaults.DEFAULT_VORNAME, dto.getVorname());
        assertEquals(true, dto.isActive());
        assertEquals(TestDefaults.FIXED_DATE, dto.getEventDate());
    }

    public static void assertFullEntity(TestEntity entity, Long expectedId, Long expectedVersion, String expectedName) {
        assertNotNull(entity);
        assertEquals(expectedId, entity.getId());
        assertEquals(expectedVersion, entity.getVersionNo());
        assertEquals(expectedName, entity.getName());
        assertEquals(TestDefaults.DEFAULT_VORNAME, entity.getVorname());
        assertEquals(true, entity.isActive());
        assertEquals(TestDefaults.FIXED_DATE, entity.getEventDate());
    }
} 
