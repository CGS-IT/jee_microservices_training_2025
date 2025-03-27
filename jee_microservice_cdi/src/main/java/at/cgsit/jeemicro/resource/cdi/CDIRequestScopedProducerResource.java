package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.producer.PBInterface;
import at.cgsit.jeemicro.cdi.req_scope_producer.ReqScopedPBInterface;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/simplecdi_request_scoped_producers")
public class CDIRequestScopedProducerResource {

    @Inject
    ReqScopedPBInterface interfaceBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Parameter(name = "X-Bean-Type", in = ParameterIn.HEADER, description = "Select which bean to use: 'a' or 'b'", required = true)
    public String requestScope() {
        String new_value = interfaceBean.echo("CDIRequestScopedProducerResource");
        return new_value;
    }
}