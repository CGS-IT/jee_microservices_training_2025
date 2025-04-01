package at.cgsit.kurs.appbeanquarkustest;

import at.cgsit.kurs.appbean.GreetingService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class GreetingServiceQuarkusTest {

  @Inject
  GreetingService service;

  @Test
  public void testGreet() {
    String result = service.greet("Quarkus");
    assertNotNull(result);
  }


}
