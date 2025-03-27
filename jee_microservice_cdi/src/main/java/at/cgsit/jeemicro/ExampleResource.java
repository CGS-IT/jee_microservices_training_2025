package at.cgsit.jeemicro;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

/**
 * initial endpoint example
 */
@Path("/hello")
public class ExampleResource {

    @Inject
    Logger LOG;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.infov("hello: {0}", 1);
        LOG.debug("debug out ");
        return "Hello from RESTEasy ddf Reactive";
    }

}