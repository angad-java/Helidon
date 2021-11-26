package io.helidon.fault;


import io.helidon.common.http.MediaType;
import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@RequestScoped
public class HelidonbarFaultToleranceApi implements IHelidonbarFaultToleranceApi {

    @Override
    @Timeout(value = 4, unit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "onFallbackMethod")
    public String timeout(int wait) {
        System.out.println("Enters HelidonbarFaultToleranceApi.timeout()");

        //WebTarget target = ClientBuilder.newClient().target("http://localhost:8081/data/hello");
        Client client = ClientBuilder.newClient();
        String name = client.target("http://localhost:8081/data/helloooo")
                .request(String.valueOf(MediaType.TEXT_PLAIN))
                .get(String.class);
        System.out.println("calling third party service result:" + name);
        //Response response = target.request().header("authorization", "Bearer " + jwt).buildGet().invoke();

        return "Response from timeout";
    }

    //execute the method three times with a delay of 500 milliseconds
    // and 200 milliseconds of randomness (called jitter).
    //The effective delay is the following: [delay – jitter, delay + jitter]
    // ( 300 to 700 milliseconds).

    @Override
    @Retry(maxDuration = 5000, maxRetries = 3, delay = 500, jitter = 200)
    @Fallback(fallbackMethod = "onFallbackMethod")
    public String retry(int wait) throws RuntimeException {
        System.out.println("Enters HelidonbarFaultToleranceApi.timeoutWithRetry()");

        Client client = ClientBuilder.newClient();
        String name = client.target("http://localhost:8081/data/hello/retry")
                .request(String.valueOf(MediaType.TEXT_PLAIN))
                .get(String.class);
        System.out.println(name);
        return "Response from timeoutWithRetryOn()";
    }


    @Override
    public String circuitBreak(int wait) throws InterruptedException  {
        for (int i=0;i<30;i++) {
            System.out.println(circuitBreak());
                Thread.sleep(500);
        }
        return "Response from circuitBreak()";
    }

//enters the open state once 50% (failureRatio=0.5) of
// five consecutive executions (requestVolumeThreshold=5) fail.
// After a delay of 500 milliseconds in the open state,
// the circuit transitions to half-open. Once ten trial executions
// (successThreshold=10) in the half-open state succeed,
// the circuit will be back in the closed state.
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 5, failureRatio = 0.5, delay = 500)
    @Fallback(fallbackMethod = "onFallbackMethod")
    public String circuitBreak() throws RuntimeException{
        System.out.println("Enters HelibarFaultToleranceApi.circuitBreak()");
        Client client = ClientBuilder.newClient();
        String name = client.target("http://localhost:8081/data/hello/retry/circuit")
                .request(String.valueOf(MediaType.TEXT_PLAIN))
                .get(String.class);
        System.out.println(name);
        return name;
    }

    //only five concurrent calls can enter this method
    @Override
    @Bulkhead(5)
    public String bulkhead() {
        System.out.println("Enters HelidonbarFaultTolerance.bulkhead()");

        return "Generated from bulkhead()";
    }

    //all your threads are occupied for a request to a (slow-responding) external system
    // five concurrent calls can enter this method,
    //execution will be on a separate thread-asynchronous
    @Override
    @Asynchronous
    @Bulkhead(value = 5, waitingTaskQueue = 10)
    public Future<Response> bulkheadAsync() throws InterruptedException {
        System.out.println("Enters HelidonbarFaultTolerance.bulkheadAsync()");
        Thread.sleep(1000);
        return CompletableFuture
                .completedFuture(
                        HelidonbarResponseGenerator.response("Generated from bulkheadAsync()"));
    }

    private Response onFallbackMethod() {
        return HelidonbarResponseGenerator.response("Generated from onFallbackMethod()");
    }

}
