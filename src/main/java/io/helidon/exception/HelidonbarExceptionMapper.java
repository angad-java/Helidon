package io.helidon.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class HelidonbarExceptionMapper implements ExceptionMapper<HelidonbarException> {
    @Override
    public Response toResponse(HelidonbarException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", e.getCode());
        error.put("message", e.getMessage());

        return Response.status(e.getCode())

                .entity(error)
                .build();
    }
}
