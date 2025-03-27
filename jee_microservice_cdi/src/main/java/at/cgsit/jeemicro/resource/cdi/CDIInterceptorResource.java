package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.logintercept.RSBeanInterceptedExample;
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

        String new_value = interfaceBean.echoReverseEntry("My Input Text Value ");
        return new_value;

    }
}