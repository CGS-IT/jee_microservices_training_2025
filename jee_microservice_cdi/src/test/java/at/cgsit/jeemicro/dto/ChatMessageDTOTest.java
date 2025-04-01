package at.cgsit.jeemicro.dto;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * keep it as quarkus test for now
 */
@QuarkusTest
class ChatMessageDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidMessage() {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(1L);
        dto.setUserName("alice");
        dto.setChatRoom("general");
        dto.setChatMessage("Hello World!");
        dto.setCreationTime(LocalDateTime.now());
        dto.setImportant(true);

        Set<ConstraintViolation<ChatMessageDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation violations");
    }

    @Test
    void testBlankUsername_shouldFail() {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setUserName("");  // invalid
        Set<ConstraintViolation<ChatMessageDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation failure for blank username");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("UserName may not be blank")));
    }

    @Test
    void testForbiddenUsername_shouldFail() {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setUserName("chris"); // forbidden due to @AssertTrue
        Set<ConstraintViolation<ChatMessageDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation failure due to forbidden username");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("this chat message is not allowed")));
    }
}
