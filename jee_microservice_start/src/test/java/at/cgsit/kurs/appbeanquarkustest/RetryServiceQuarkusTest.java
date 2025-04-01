package at.cgsit.kurs.appbeanquarkustest;

import at.cgsit.kurs.appbean.RetryService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;



@QuarkusTest
public class RetryServiceQuarkusTest {

  @Inject
  RetryService retryService;

  @BeforeEach
  void resetState() {
    retryService.reset(); // clear state before each repetition
  }

  @RepeatedTest(5)
    //@Order(1)
  void shouldEventuallySucceedWithinRetries() {
    boolean result = false;
    for (int i = 0; i < 3; i++) {
      result = retryService.tryOperationWithRetry();
      if (result) break;
    }
    assertTrue(result, "Operation should succeed within retry limit");
  }

}
