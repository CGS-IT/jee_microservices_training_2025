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

        entity.setLocalDate(LocalDate.of(2024, 1, 1));
        entity.setLocalTime(LocalTime.of(10, 30));
        entity.setLocalDateTime(LocalDateTime.of(2024, 1, 1, 10, 30));
        entity.setOffsetDateTime(OffsetDateTime.of(2024, 1, 1, 10, 30, 0, 0, ZoneOffset.UTC));
        entity.setInstant(Instant.parse("2024-01-01T10:30:00Z"));
        entity.setLegacyDate(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime());
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 1);
        entity.setLegacyCalendar(cal);

        entity.setBigDecimal(new BigDecimal("12345.6789"));
        entity.setBigInteger(new BigInteger("987654321"));
        UUID uuid = UUID.randomUUID();
        entity.setUuid(uuid);
        byte[] binary = "Hello Binary".getBytes();
        entity.setBinaryData(binary);

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
        assertEquals(42, loaded.getPrimitiveInt());
        assertEquals(420, loaded.getWrapperInt());
        assertEquals(123456789L, loaded.getPrimitiveLong());
        assertEquals(987654321L, loaded.getWrapperLong());
        assertEquals(3.14, loaded.getPrimitiveDouble());
        assertEquals(2.718, loaded.getWrapperDouble());
        assertEquals(1.23f, loaded.getPrimitiveFloat());
        assertEquals(4.56f, loaded.getWrapperFloat());
        assertTrue(loaded.isPrimitiveBoolean());
        assertFalse(loaded.getWrapperBoolean());
        assertEquals((byte)1, loaded.getPrimitiveByte());
        assertEquals((byte)2, loaded.getWrapperByte());
        assertEquals((short)10, loaded.getPrimitiveShort());
        assertEquals((short)20, loaded.getWrapperShort());
        assertEquals('A', loaded.getPrimitiveChar());
        assertEquals(Character.valueOf('B'), loaded.getWrapperChar());

        assertEquals("Test String", loaded.getStringValue());
        assertEquals(SampleEnum.ACTIVE, loaded.getEnumValue());
        assertEquals("This is a large text block.", loaded.getLargeText());

        assertEquals(LocalDate.of(2024, 1, 1), loaded.getLocalDate());
        assertEquals(LocalTime.of(10, 30), loaded.getLocalTime());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 30), loaded.getLocalDateTime());
        assertEquals(OffsetDateTime.of(2024, 1, 1, 10, 30, 0, 0, ZoneOffset.UTC), loaded.getOffsetDateTime());
        assertEquals(Instant.parse("2024-01-01T10:30:00Z"), loaded.getInstant());
        assertEquals(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime(), loaded.getLegacyDate());
        assertEquals(cal.get(Calendar.YEAR), loaded.getLegacyCalendar().get(Calendar.YEAR));

        assertEquals(new BigDecimal("12345.6789"), loaded.getBigDecimal());
        assertEquals(new BigInteger("987654321"), loaded.getBigInteger());
        assertEquals(uuid, loaded.getUuid());
        assertArrayEquals(binary, loaded.getBinaryData());

        assertTrue(loaded.getStringList().contains("tag2"));
        assertEquals("value1", loaded.getStringMap().get("key1"));
        assertEquals("value2", loaded.getStringMap().get("key2"));
    }
}
