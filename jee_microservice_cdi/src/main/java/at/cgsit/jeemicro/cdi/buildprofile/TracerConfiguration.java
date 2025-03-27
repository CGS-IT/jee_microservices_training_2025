package at.cgsit.jeemicro.cdi.buildprofile;

import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.Produces;
import jdk.javadoc.doclet.Reporter;

@Dependent
public class TracerConfiguration {

    @Produces
    @IfBuildProfile("prod")
    public Tracer realTracer() {
        return new TracerImplTwo();
    }

    @Produces
    @DefaultBean
    public Tracer noopTracer() {
        return new TracerImplTwo();
    }
}