package io.helidon.fault;


import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class CircuitBreakerOpenExceptionHandler implements FallbackHandler<Response> {
    @Override
    public Response handle(ExecutionContext executionContext) {
        return HelidonbarResponseGenerator.response("Response from CircuitBreakerOpenExceptionHandler");
    }
}
