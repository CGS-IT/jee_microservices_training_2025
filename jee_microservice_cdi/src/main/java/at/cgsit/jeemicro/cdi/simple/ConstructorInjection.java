package at.cgsit.jeemicro.cdi.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@RequestScoped
public class ConstructorInjection {

    @Inject
    Logger log;

    SimpleCDIBean cdiBean;

    @Inject
    public ConstructorInjection(SimpleCDIBean cdiBean) {
        this.cdiBean = cdiBean;
    }

    public String echo(String input) {
        log.info("SetterInjection");
        return cdiBean.echo(input);
    }
}