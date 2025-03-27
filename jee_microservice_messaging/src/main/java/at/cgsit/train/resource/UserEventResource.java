package at.cgsit.train.resource;

import at.cgsit.train.producer.UserEventProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/events")
public class UserEventResource {

    @Inject
    UserEventProducer producer;

    @POST
    public Response sendEvent() {
        producer.send("Hello from REST endpoint!");
        return Response.accepted().build();
    }
}
