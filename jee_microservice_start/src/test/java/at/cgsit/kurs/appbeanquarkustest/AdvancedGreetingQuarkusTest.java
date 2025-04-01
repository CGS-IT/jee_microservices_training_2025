package at.cgsit.kurs.appbeanquarkustest;


import at.cgsit.kurs.appbean.AdvancedGreetingService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * here we test the real service not mocking anything
 *
 */
@QuarkusTest
public class AdvancedGreetingQuarkusTest {

  @Inject
  AdvancedGreetingService greetingService;

  @Test
  public void testGreetWithMockedTime() {

    String result = greetingService.greet("Alice");
    assertNotNull(result);
  }

}
