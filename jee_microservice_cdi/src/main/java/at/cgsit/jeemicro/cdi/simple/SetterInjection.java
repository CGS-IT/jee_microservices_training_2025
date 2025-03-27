package at.cgsit.jeemicro.cdi.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@RequestScoped
public class SetterInjection {

    @Inject
    Logger log;

    SimpleCDIBean cdiBean;

    @Inject
    public void setCdiBean(SimpleCDIBean cdiBean) {
        log.info("set CDI Bean in SetterInjection");
        this.cdiBean = cdiBean;
    }

    public String echo(String input) {
        log.info("Echo: SetterInjection");
        return cdiBean.echo(input);
    }

}
