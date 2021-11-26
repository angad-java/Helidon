/*
package io.helidon.example.jpa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import io.helidon.microprofile.server.Main;
import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    private static int port;

    @BeforeAll
    public static void startTheServer() throws Exception {
        Main.main(new String[0]);
        port = Main.serverPort();
    }

    @Test
    void testRestResource() {
        Client client = ClientBuilder.newClient();

        String response = client
                .target(getConnectionString("/example/hello"))
                .request()
                .get(String.class);
        Assertions.assertEquals("It works!", response);
    }

    @Test
    void testRoot(){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(getConnectionString("/"))
                .request()
                .get();
        Assertions.assertEquals(404, response.getStatus());
    }

    private static String getConnectionString(String path) {
        return "http://localhost:" + port + path;
    }
}
*/
