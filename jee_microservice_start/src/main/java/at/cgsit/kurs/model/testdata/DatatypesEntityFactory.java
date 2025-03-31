package at.cgsit.kurs.model.testdata;

import at.cgsit.kurs.model.DatatypesEntity;
import at.cgsit.kurs.model.SampleEnum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

public class DatatypesEntityFactory {

    public static DatatypesEntity createDefault() {
        DatatypesEntity entity = new DatatypesEntity();

        // Primitive + Wrapper
        entity.setPrimitiveInt(42);
        entity.setWrapperInt(123);

        entity.setPrimitiveLong(1_000_000L);
        entity.setWrapperLong(2_000_000L);

        entity.setPrimitiveDouble(3.14);
        entity.setWrapperDouble(6.28);

        entity.setPrimitiveFloat(1.23f);
        entity.setWrapperFloat(4.56f);

        entity.setPrimitiveBoolean(true);
        entity.setWrapperBoolean(Boolean.FALSE);

        entity.setPrimitiveByte((byte) 8);
        entity.setWrapperByte((byte) 16);

        entity.setPrimitiveShort((short) 100);
        entity.setWrapperShort((short) 200);

        entity.setPrimitiveChar('A');
        entity.setWrapperChar('B');

        // Strings and Enums
        entity.setStringValue("Test String");
        entity.setEnumValue(SampleEnum.ACTIVE);
        entity.setLargeText("This is a large block of text for @Lob fields.");

        // Temporal
        entity.setLocalDate(LocalDate.now());
        entity.setLocalTime(LocalTime.now());
        entity.setLocalDateTime(LocalDateTime.now());
        entity.setOffsetDateTime(OffsetDateTime.now());
        entity.setInstant(Instant.now());

        entity.setLegacyDate(new Date());
        entity.setLegacyCalendar(Calendar.getInstance());

        // Numeric
        entity.setBigDecimal(new BigDecimal("123456.7890"));
        entity.setBigInteger(new BigInteger("98765432101234567890"));

        // UUID and binary
        entity.setUuid(UUID.randomUUID());
        entity.setBinaryData("Hello Binary!".getBytes());

        // Collection fields
        entity.setStringList(List.of("alpha", "beta", "gamma"));

        Map<String, String> attributes = new HashMap<>();
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        entity.setStringMap(attributes);

        // JSON string (simulate real data structure)
        // entity.setJsonString("{\"foo\":\"bar\", \"count\":42}");

        return entity;
    }
}
