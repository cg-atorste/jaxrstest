package fi.capgemini.atorste.jaxrstest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // setup client
        Client client = ClientBuilder.newClient();
        String baseUrl = "https://httpbin.org/anything";
        WebTarget target = client.target(baseUrl + "/api/put");

        // initialize request data
        String metric = "foo";
        Instant timestamp = Instant.now();
        long value = 42;
        Map<String, String> tags = new HashMap<>();
        tags.put("bar", "baz");
        RequestData data = new RequestData(metric, timestamp, value, tags);

        // send request
        Response response = target
                .request()
                .buildPost(Entity.json(data))
                .invoke();

        // print response
        try {
            System.out.println("Status: " + response.getStatusInfo());
            System.out.println("Headers:");
            for (Map.Entry<String, List<String>> header : response.getStringHeaders().entrySet()) {
                System.out.println(String.format("%s: %s", header.getKey(), String.join(";", header.getValue())));
            }
            System.out.println("END OF HEADERS");
            System.out.println("");
            System.out.println("Entity:");
            System.out.println(response.readEntity(String.class));
            System.out.println("END OF ENTITY");
        } finally {
            response.close();
        }
    }
}
