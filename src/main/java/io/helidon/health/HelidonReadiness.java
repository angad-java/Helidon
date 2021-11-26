package io.helidon.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Readiness
@ApplicationScoped
public class HelidonReadiness implements HealthCheck {
    private AtomicLong readyTime = new AtomicLong(0);
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("HelidonReadiness")
                .state(isReady())
                .build();
    }

    public void onStartUp(@Observes @Initialized(ApplicationScoped.class) Object init) {
        readyTime = new AtomicLong(System.currentTimeMillis());
    }

    private boolean isReady() {
        return Duration.ofMillis(System.currentTimeMillis() - readyTime.get()).getSeconds() >=5;
    }
}
