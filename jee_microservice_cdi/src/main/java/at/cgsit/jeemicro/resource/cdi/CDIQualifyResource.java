package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.interfacebean.InterfaceBean;
import at.cgsit.jeemicro.cdi.qualify.QBean;
import at.cgsit.jeemicro.cdi.qualify.qualifier.QualifyA;
import at.cgsit.jeemicro.cdi.qualify.qualifier.QualifyB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_qualify")
public class CDIQualifyResource {

    @Inject
    @QualifyA
    QBean interfaceBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        String new_value = interfaceBean.echo("CDIQualifyResource");

        return new_value;
    }
}