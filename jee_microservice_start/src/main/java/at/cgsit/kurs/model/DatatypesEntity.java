package at.cgsit.kurs.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "datatypes")
public class DatatypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    // --- Primitives and Wrappers ---

    @Column(name = "primitive_int")
    private int primitiveInt;

    @Column(name = "wrapper_int")
    private Integer wrapperInt;

    @Column(name = "primitive_long")
    private long primitiveLong;

    @Column(name = "wrapper_long")
    private Long wrapperLong;

    @Column(name = "primitive_double")
    private double primitiveDouble;

    @Column(name = "wrapper_double")
    private Double wrapperDouble;

    @Column(name = "primitive_float")
    private float primitiveFloat;

    @Column(name = "wrapper_float")
    private Float wrapperFloat;

    @Column(name = "primitive_boolean")
    private boolean primitiveBoolean;

    @Column(name = "wrapper_boolean")
    private Boolean wrapperBoolean;

    @Column(name = "primitive_byte")
    private byte primitiveByte;

    @Column(name = "wrapper_byte")
    private Byte wrapperByte;

    @Column(name = "primitive_short")
    private short primitiveShort;

    @Column(name = "wrapper_short")
    private Short wrapperShort;

    @Column(name = "primitive_char", length = 1)
    private char primitiveChar;

    @Column(name = "wrapper_char", length = 1)
    private Character wrapperChar;

    // --- Text and Enums ---

    @Column(name = "string_value", length = 255)
    private String stringValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_value", length = 32)
    private SampleEnum enumValue;

    @Lob
    @Column(name = "large_text", columnDefinition = "TEXT")
    private String largeText;

    // --- Temporal Types ---

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "local_time")
    private LocalTime localTime;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Column(name = "offset_date_time")
    private OffsetDateTime offsetDateTime;

    @Column(name = "instant")
    private Instant instant;

    @Temporal(TemporalType.DATE)
    @Column(name = "legacy_date")
    private Date legacyDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "legacy_calendar")
    private Calendar legacyCalendar;

    // --- Numeric Types ---

    @Column(name = "big_decimal", precision = 20, scale = 6)
    private BigDecimal bigDecimal;

    @Column(name = "big_integer", precision = 38)
    private BigInteger bigInteger;

    // --- UUID / Binary ---

    @Column(name = "uuid_value", columnDefinition = "UUID")
    private UUID uuid;

    @Lob
    @Column(name = "binary_data")
    private byte[] binaryData;

    // --- Collection Types ---

    @ElementCollection
    @CollectionTable(name = "datatypes_tags", joinColumns = @JoinColumn(name = "datatype_id"))
    @Column(name = "tag", length = 100)
    private List<String> stringList;

    @ElementCollection
    @CollectionTable(name = "datatypes_attributes", joinColumns = @JoinColumn(name = "datatype_id"))
    @MapKeyColumn(name = "attribute_key", length = 50)
    @Column(name = "attribute_value", length = 255)
    private Map<String, String> stringMap;

    // --- JSON-like Field (for PostgreSQL, requires db support) ---
    // @Column(name = "json_string", columnDefinition = "JSONB")
    // private String jsonString;

    // --- Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrimitiveInt() {
        return primitiveInt;
    }

    public void setPrimitiveInt(int primitiveInt) {
        this.primitiveInt = primitiveInt;
    }

    public Integer getWrapperInt() {
        return wrapperInt;
    }

    public void setWrapperInt(Integer wrapperInt) {
        this.wrapperInt = wrapperInt;
    }

    public long getPrimitiveLong() {
        return primitiveLong;
    }

    public void setPrimitiveLong(long primitiveLong) {
        this.primitiveLong = primitiveLong;
    }

    public Long getWrapperLong() {
        return wrapperLong;
    }

    public void setWrapperLong(Long wrapperLong) {
        this.wrapperLong = wrapperLong;
    }

    public double getPrimitiveDouble() {
        return primitiveDouble;
    }

    public void setPrimitiveDouble(double primitiveDouble) {
        this.primitiveDouble = primitiveDouble;
    }

    public Double getWrapperDouble() {
        return wrapperDouble;
    }

    public void setWrapperDouble(Double wrapperDouble) {
        this.wrapperDouble = wrapperDouble;
    }

    public float getPrimitiveFloat() {
        return primitiveFloat;
    }

    public void setPrimitiveFloat(float primitiveFloat) {
        this.primitiveFloat = primitiveFloat;
    }

    public Float getWrapperFloat() {
        return wrapperFloat;
    }

    public void setWrapperFloat(Float wrapperFloat) {
        this.wrapperFloat = wrapperFloat;
    }

    public boolean isPrimitiveBoolean() {
        return primitiveBoolean;
    }

    public void setPrimitiveBoolean(boolean primitiveBoolean) {
        this.primitiveBoolean = primitiveBoolean;
    }

    public Boolean getWrapperBoolean() {
        return wrapperBoolean;
    }

    public void setWrapperBoolean(Boolean wrapperBoolean) {
        this.wrapperBoolean = wrapperBoolean;
    }

    public byte getPrimitiveByte() {
        return primitiveByte;
    }

    public void setPrimitiveByte(byte primitiveByte) {
        this.primitiveByte = primitiveByte;
    }

    public Byte getWrapperByte() {
        return wrapperByte;
    }

    public void setWrapperByte(Byte wrapperByte) {
        this.wrapperByte = wrapperByte;
    }

    public short getPrimitiveShort() {
        return primitiveShort;
    }

    public void setPrimitiveShort(short primitiveShort) {
        this.primitiveShort = primitiveShort;
    }

    public Short getWrapperShort() {
        return wrapperShort;
    }

    public void setWrapperShort(Short wrapperShort) {
        this.wrapperShort = wrapperShort;
    }

    public char getPrimitiveChar() {
        return primitiveChar;
    }

    public void setPrimitiveChar(char primitiveChar) {
        this.primitiveChar = primitiveChar;
    }

    public Character getWrapperChar() {
        return wrapperChar;
    }

    public void setWrapperChar(Character wrapperChar) {
        this.wrapperChar = wrapperChar;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public SampleEnum getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(SampleEnum enumValue) {
        this.enumValue = enumValue;
    }

    public String getLargeText() {
        return largeText;
    }

    public void setLargeText(String largeText) {
        this.largeText = largeText;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Date getLegacyDate() {
        return legacyDate;
    }

    public void setLegacyDate(Date legacyDate) {
        this.legacyDate = legacyDate;
    }

    public Calendar getLegacyCalendar() {
        return legacyCalendar;
    }

    public void setLegacyCalendar(Calendar legacyCalendar) {
        this.legacyCalendar = legacyCalendar;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }
}
