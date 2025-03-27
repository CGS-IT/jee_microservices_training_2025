package at.cgsit.jeemicro.cdi.buildprofile;

import io.opentelemetry.api.trace.Tracer;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Produces;

public class TracerConfiguration {

    @Produces
    // @IfBuildProfile("prod")
    @RequestScoped
    public MyTracer realTracer() {
        return new TracerImplTwo();
    }
    @Produces
    @IfBuildProfile("dev")
    public MyTracer noopTracer() {
        return new TracerImpl();
    }

}