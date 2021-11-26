package io.helidon.exception;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class HelidonbarResponseGenerator {
    public static final Response response(String message) {
        Map<String, String> data = new HashMap<>();
        data.put("value", message);

        return Response.ok()
                .entity(data)
                .build();
    }
}
