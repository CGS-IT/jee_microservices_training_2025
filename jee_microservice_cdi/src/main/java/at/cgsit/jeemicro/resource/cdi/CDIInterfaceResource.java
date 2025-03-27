package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.interfacebean.InterfaceBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_interface")
public class CDIInterfaceResource {

    @Inject
    InterfaceBean interfaceBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        String new_value = interfaceBean.echo("interfaceBean");
        return new_value;
    }
}
