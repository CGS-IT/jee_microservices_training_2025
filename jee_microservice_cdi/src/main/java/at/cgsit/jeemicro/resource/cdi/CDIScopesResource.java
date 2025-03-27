package at.cgsit.jeemicro.resource.cdi;

import at.cgsit.jeemicro.cdi.logintercept.RSBeanInterceptedExample;
import at.cgsit.jeemicro.cdi.requestscope.ApplicationScopeBean;
import at.cgsit.jeemicro.cdi.requestscope.RSBean;
import io.quarkus.runtime.configuration.SystemOnlySourcesConfigBuilder;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi_scopes")
public class CDIScopesResource {

    @Inject
    RSBean rsBean;

    @Inject
    ApplicationScopeBean asBean;

    @GET
    @Path("/requestScope")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        rsBean.setRequestScopedMessage("Kontonummer zum Buchen: 1");
        String value = rsBean.getRequestScopedMessage();
        rsBean.setRequestScopedMessage("Kontonummer zum Buchen: 2");
        return value;
    }

    @GET
    @Path("/applicationScope")
    @Produces(MediaType.TEXT_PLAIN)
    public String applicationScope() {
        Integer value = asBean.getCounter();
        value = asBean.getCounter();
        return value.toString();
    }


}