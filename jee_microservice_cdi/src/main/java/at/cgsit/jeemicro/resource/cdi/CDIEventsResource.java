package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.events.SpecialService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;


@Path("/simplecdi_events")
public class CDIEventsResource {

    @Inject
    Logger log;

    @Inject
    SpecialService specialService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        specialService.doSomething();
        log.info("CDIEventsResource called");
        return "";
    }

}
