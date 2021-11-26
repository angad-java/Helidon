package io.helidon.metrics;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;


@ApplicationScoped
public class HelidonbarMetricsGauge {
    private AtomicLong startTime = new AtomicLong(0);

    public void onStartUp(@Observes @Initialized(ApplicationScoped.class) Object init) {
        startTime = new AtomicLong(System.currentTimeMillis());
    }

    @Gauge(unit = MetricUnits.SECONDS)
    public long upTime() {
        return Duration.ofMillis(System.currentTimeMillis() - startTime.get()).getSeconds();
    }
}
