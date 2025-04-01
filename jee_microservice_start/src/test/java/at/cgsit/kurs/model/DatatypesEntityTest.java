package at.cgsit.kurs.model;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DatatypesEntityTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void testPersistAndLoadDatatypesEntity() {
        DatatypesEntity entity = new DatatypesEntity();

        entity.setPrimitiveInt(42);
        entity.setWrapperInt(420);
        entity.setPrimitiveLong(123456789L);
        entity.setWrapperLong(987654321L);
        entity.setPrimitiveDouble(3.14);
        entity.setWrapperDouble(2.718);
        entity.setPrimitiveFloat(1.23f);
        entity.setWrapperFloat(4.56f);
        entity.setPrimitiveBoolean(true);
        entity.setWrapperBoolean(Boolean.FALSE);
        entity.setPrimitiveByte((byte) 1);
        entity.setWrapperByte((byte) 2);
        entity.setPrimitiveShort((short) 10);
        entity.setWrapperShort((short) 20);
        entity.setPrimitiveChar('A');
        entity.setWrapperChar('B');

        entity.setStringValue("Test String");
        entity.setEnumValue(SampleEnum.ACTIVE);
        entity.setLargeText("This is a large text block.");

        entity.setLocalDate(LocalDate.now());
        entity.setLocalTime(LocalTime.now());
        entity.setLocalDateTime(LocalDateTime.now());
        entity.setOffsetDateTime(OffsetDateTime.now());
        entity.setInstant(Instant.now());
        entity.setLegacyDate(new Date());
        entity.setLegacyCalendar(Calendar.getInstance());

        entity.setBigDecimal(new BigDecimal("12345.6789"));
        entity.setBigInteger(new BigInteger("987654321"));
        entity.setUuid(UUID.randomUUID());
        entity.setBinaryData("Hello Binary".getBytes());

        entity.setStringList(Arrays.asList("tag1", "tag2"));
        Map<String, String> attributes = new HashMap<>();
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        entity.setStringMap(attributes);

        em.persist(entity);
        em.flush();
        em.clear();

        DatatypesEntity loaded = em.find(DatatypesEntity.class, entity.getId());
        assertNotNull(loaded);
        assertEquals("Test String", loaded.getStringValue());
        assertEquals(SampleEnum.ACTIVE, loaded.getEnumValue());
        assertEquals("value1", loaded.getStringMap().get("key1"));
        assertTrue(loaded.getStringList().contains("tag2"));
    }
}
