package at.cgsit.kurs.appbean;


import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@QuarkusComponentTest  // This is the alias of @QuarkusComponentTest
class GreetingServiceTest {

  @Inject
  GreetingService service;

  @Test
  public void testGreet() {
    String result = service.greet("Quarkus");
    assertEquals("Hello, Quarkus!", result);
  }
}