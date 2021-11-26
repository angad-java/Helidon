package io.helidon.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class HelidonbarLiveness implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("HelidonLiveness")
                .up()
                .withData("time", System.currentTimeMillis())
                .build();
    }
}
