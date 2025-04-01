package at.cgsit.jeemicro.cdi.alternatives;

import at.cgsit.jeemicro.cdi.alternatives.testprofile.MainAlternativeProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(MainAlternativeProfile.class)
public class AlternativeBeanImplMainTest {

    @Inject
    AlternativeBeanInterface bean;

    @Test
    void testEchoWithRealBean() {
        String result = bean.echo("quarkus");
        assertEquals("sukrauq", result);
    }
}
