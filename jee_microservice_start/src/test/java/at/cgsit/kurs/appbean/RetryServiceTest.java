package at.cgsit.kurs.appbean;

import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusComponentTest(RetryService.class)
@Tag("business-logic")
@Tag("retry")
public class RetryServiceTest {

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
