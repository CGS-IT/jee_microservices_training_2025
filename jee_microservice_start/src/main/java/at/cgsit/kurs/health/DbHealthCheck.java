package at.cgsit.kurs.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness; // or @Readiness
import org.eclipse.microprofile.health.Readiness;

@Readiness
// @Liveness or use @Readiness for readiness probes
@ApplicationScoped
public class DbHealthCheck implements HealthCheck {

    @Inject
    EntityManager em;

    /**
     * For PostgreSQL, MySQL, H2, just use SELECT 1.
     * For Oracle, use SELECT 1 FROM DUAL.
     *
     * @return
     */
    @Override
    public HealthCheckResponse call() {
        try {
            em.createNativeQuery("SELECT 1");
            return HealthCheckResponse.up("database");
        } catch (Exception e) {
            return HealthCheckResponse.down("database");
        }
    }
}
