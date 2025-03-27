package at.cgsit.jeemicro.cdi.alternatives.mock;

import at.cgsit.jeemicro.cdi.alternatives.AlternativeBeanInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.Serializable;

@Alternative
@ApplicationScoped
public class AlternativeBeanImplDummy implements AlternativeBeanInterface, Serializable {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("AlternativeBeanImplDummy called");
        return "AlternativeBeanImplDummy info";
    }
}
