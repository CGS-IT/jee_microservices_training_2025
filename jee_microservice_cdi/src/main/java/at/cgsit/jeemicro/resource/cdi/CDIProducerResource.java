package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.alternatives.AlternativeBeanInterface;
import at.cgsit.jeemicro.cdi.producer.PBInterface;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_producers")
public class CDIProducerResource {

    @Inject
    PBInterface interfaceBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        String new_value = interfaceBean.echo("CDIProducerResource");
        return new_value;
    }
}