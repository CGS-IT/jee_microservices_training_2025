package at.cgsit.jeemicro.cdi.alternatives.testprofile;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

/**
 * MainAlternativeProfile for testing.
 * we enfore the use of AlternativeBeanImplMain in the test. not the mock as configured in the application.properties
 */
public class MainAlternativeProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
            "quarkus.arc.selected-alternatives",
            "at.cgsit.jeemicro.cdi.alternatives.AlternativeBeanImplMain"
        );
    }
}
