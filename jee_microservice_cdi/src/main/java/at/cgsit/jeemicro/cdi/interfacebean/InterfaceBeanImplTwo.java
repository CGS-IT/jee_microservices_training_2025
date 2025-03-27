package at.cgsit.jeemicro.cdi.interfacebean;

import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;


@IfBuildProperty(name = "at.cgsit.train.ibean", stringValue = "bean2")
@RequestScoped
public class InterfaceBeanImplTwo implements InterfaceBean {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("InterfaceBeanImplTwo !! echo called");
        return "InterfaceBeanImpl !! TWO !! : " + input;
    }
}
