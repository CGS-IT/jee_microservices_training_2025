package at.cgsit.kurs.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ChildDtoTest {

    @Test
    void testChildDtoGettersSettersAndConstructor() {
        Instant now = Instant.now();
        ChildDto dto = new ChildDto(1L, "My Child", now, 99L);

        assertEquals(1L, dto.getId());
        assertEquals("My Child", dto.getChildName());
        assertEquals(now, dto.getCreatedAt());
        assertEquals(99L, dto.getParentId());
    }

    @Test
    void testChildDtoSettersAndDefaults() {
        ChildDto dto = new ChildDto();
        dto.setId(2L);
        dto.setChildName("Another Child");
        Instant now = Instant.now();
        dto.setCreatedAt(now);
        dto.setParentId(100L);

        assertEquals(2L, dto.getId());
        assertEquals("Another Child", dto.getChildName());
        assertEquals(now, dto.getCreatedAt());
        assertEquals(100L, dto.getParentId());
    }

    @Test
    void testDefaultCreatedAtIsNotNull() {
        ChildDto dto = new ChildDto();
        assertNotNull(dto.getCreatedAt());
    }
} 
