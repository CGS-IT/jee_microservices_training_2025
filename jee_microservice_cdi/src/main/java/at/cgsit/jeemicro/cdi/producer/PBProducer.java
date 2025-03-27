package at.cgsit.jeemicro.cdi.producer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

public class PBProducer {

    private static Logger log = Logger.getLogger(PBProducer.class);

    @ConfigProperty(name = "at.cgs.training.produceBean", defaultValue = "a")
    String beantoProduce;

    @Produces
    @RequestScoped
    PBInterface producePB() {
        log.info("producer called");
        if( "a".equalsIgnoreCase(beantoProduce)){
            PBImplA a = new PBImplA();
            return a;
        }
        return new PBImplB();
    }
}



