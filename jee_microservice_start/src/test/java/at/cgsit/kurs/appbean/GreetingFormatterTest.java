package at.cgsit.kurs.appbean;

import io.quarkus.test.component.QuarkusComponentTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * if no bean is required, use a simple junit5 test instead of a QuarkusComponentTest
 * else use a QuarkusComponentTest or a QuarkusTest if you also use the database / JPA
 */
public class GreetingFormatterTest {

    public String formatGreeting(String name) {
        return "Hello " + name + "!";
    }

    @ParameterizedTest
    @CsvSource({
        "Alice, Hello Alice!",
        "Bob, Hello Bob!",
        "Quarkus, Hello Quarkus!",
        "Quarkus2, Hello Quarkus2!",
    })
    void testFormatGreeting(String input, String expected) {
        assertEquals(expected, formatGreeting(input));
    }
}
