package at.cgsit.jeemicro.cdi.req_scope_producer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
@RequestScoped
public class ReqScopedPBProducer {

    private static Logger log = Logger.getLogger(ReqScopedPBProducer.class);

    @Inject
    HttpHeaders headers;

    @Produces
    @RequestScoped
    ReqScopedPBInterface producePB() {
        log.info("ReqScopedPBProducer producer called");
        // X-Bean-Type = b oder a
        String which = headers.getHeaderString("X-Bean-Type");
        if ("a".equalsIgnoreCase(which)) {
            return new ReqScopedPBImplA();
        }
        return new ReqScopedPBImplB();
    }
}



