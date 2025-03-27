package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.buildprofile.MyTracer;
import at.cgsit.jeemicro.cdi.producer.PBInterface;
import io.opentelemetry.api.trace.Tracer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_build_profile")
public class CDIBuildProfileResource {

    @Inject
    MyTracer bean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        String new_value = bean.echo("CDIBuildProfileResource");
        return new_value;
    }
}