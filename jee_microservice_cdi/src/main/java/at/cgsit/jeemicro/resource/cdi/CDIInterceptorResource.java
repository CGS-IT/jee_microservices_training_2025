package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.logintercept.RSBeanInterceptedExample;
import at.cgsit.jeemicro.cdi.producer.PBInterface;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_interceptor")
public class CDIInterceptorResource {

    @Inject
    RSBeanInterceptedExample interfaceBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {

        String new_value = interfaceBean.echoReverse2("CDIProducerResource");
        return new_value;

    }
}