package hello;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HelloHealthCheck implements HealthIndicator {
//----------------------------------------------------------------------------------------------------------------------
// HealthIndicator Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public Health health() {
        return Health.up().withDetail("message", "We're cool and the gang!").build();
    }
}
