package at.cgsit.kurs.appbean;

import io.quarkus.test.component.QuarkusComponentTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusComponentTest
public class GreetingFormatterTest {

    public String formatGreeting(String name) {
        return "Hello " + name + "!";
    }

    @ParameterizedTest
    @CsvSource({
        "Alice, Hello Alice!",
        "Bob, Hello Bob!",
        "Quarkus, Hello Quarkus!"
    })
    void testFormatGreeting(String input, String expected) {
        assertEquals(expected, formatGreeting(input));
    }
}
