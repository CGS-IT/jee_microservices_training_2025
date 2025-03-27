package at.cgsit.jeemicro.resource.cdi;


import at.cgsit.jeemicro.cdi.simple.ConstructorInjection;
import at.cgsit.jeemicro.cdi.simple.SetterInjection;
import at.cgsit.jeemicro.cdi.simple.SimpleCDIBean;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/simplecdi")
public class CDISimpleResource {

    @Inject
    private SimpleCDIBean cdiBean;

    @Inject
    private SetterInjection setterInjection;

    @Inject
    @Default
    private ConstructorInjection constructorInjection;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {

        String new_value = cdiBean.echo("new value");

        String setter_injection = setterInjection.echo("setter injection");
        String constructor_injection = constructorInjection.echo("constructor injection");
        return new_value + " " + setter_injection + " " + constructor_injection;
    }
}
