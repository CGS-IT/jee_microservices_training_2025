package at.cgsit.jeemicro.cdi.qualify;

import at.cgsit.jeemicro.cdi.qualify.qualifier.QualifyB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.Serializable;

@QualifyB
@ApplicationScoped
public class QBeanImplB implements QBean, Serializable {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("QBeanImplB called");
        return "QBeanImplB called";
    }
}
