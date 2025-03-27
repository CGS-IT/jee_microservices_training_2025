package at.cgsit.kurs.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * check your memory usage
 */
@Readiness
@ApplicationScoped
public class MemoryHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        long usagePercent = (usedMemory * 100) / maxMemory;

        boolean healthy = usagePercent < 95;

        return HealthCheckResponse.named("MemoryHealthCheck")
                .status(healthy)
                .withData("maxMemoryMB", maxMemory / (1024 * 1024))
                .withData("usedMemoryMB", usedMemory / (1024 * 1024))
                .withData("usagePercent", usagePercent)
                .withData("status", healthy ? "OK" : "Memory usage high")
                .build();
    }
}
