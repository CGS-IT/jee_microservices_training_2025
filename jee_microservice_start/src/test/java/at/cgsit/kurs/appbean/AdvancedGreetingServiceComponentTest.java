package at.cgsit.kurs.appbean;

import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusComponentTest
public class AdvancedGreetingServiceComponentTest {

    @InjectMock
    TimeService timeService; // this will replace the real bean

    @Inject
    AdvancedGreetingService greetingService;

    @Test
    public void testGreetWithMockedTime() {

        when(timeService.getCurrentTime()).thenReturn("12:00");

        String result = greetingService.greet("Alice");
        assertEquals("Hello, Alice! Time is: 12:00", result);
    }
}
