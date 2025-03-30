package at.cgsit.kurs.testutil;

import at.cgsit.kurs.data.TestNames;

import java.time.Instant;
import java.util.Date;

public final class TestDefaults {

    public static final Long VALID_ID = 100L;
    public static final Long VALID_VERSION = 1L;
    public static final String DEFAULT_VORNAME = TestNames.HERR_MANN.getVorname();
    public static final String VALID_NAME = TestNames.HERR_MANN.getName();
    public static final Date FIXED_DATE = Date.from(Instant.parse("2025-01-01T00:00:00.00Z"));
    public static final String CHILD_NAME = "child1";

    private TestDefaults() {
        // Prevent instantiation
    }
}
