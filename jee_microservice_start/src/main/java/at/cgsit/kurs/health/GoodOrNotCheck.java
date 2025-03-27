package at.cgsit.kurs.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Liveness
@ApplicationScoped
public class GoodOrNotCheck implements HealthCheck {

    @ConfigProperty(name = "at.cgs.training.goodorNot", defaultValue = "true")
    Boolean good;

    @Override
    public HealthCheckResponse call() {
        if (good) {
            return HealthCheckResponse.named("GoodOrNotCheck")
                .withData("status", "up")
                .withData("reason", "Property 'good' is set to true")
                .withData("timestamp", System.currentTimeMillis())
                .up()
                .build();
        } else {
            return HealthCheckResponse.named("GoodOrNotCheck")
                .withData("status", "down")
                .withData("reason", "Property 'good' is false")
                .withData("timestamp", System.currentTimeMillis())
                .down()
                .build();
        }
    }
}