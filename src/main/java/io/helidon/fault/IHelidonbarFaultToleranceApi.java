package io.helidon.fault;


import io.helidon.exception.HelidonbarException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;

@Path("/tolerance")
@ApplicationScoped
public interface IHelidonbarFaultToleranceApi {
    @GET
    @Path("/timeout")
    String timeout(@QueryParam("wait") int wait) throws HelidonbarException, InterruptedException;

    @GET
    @Path("/retryOn")
    String retry(@QueryParam("wait") int wait);

    @GET
    @Path("/circuitBreak")
    String circuitBreak(@QueryParam("wait") int wait) throws InterruptedException;

    @GET
    @Path("/bulkhead")
    String bulkhead();

    @GET
    @Path("/bulkhead-async")
    Future<Response> bulkheadAsync() throws InterruptedException;
}
