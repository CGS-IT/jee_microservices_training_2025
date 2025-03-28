package at.cgsit.jeemicro.cdi.interfacebean;

import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

// ifBuildProperty is used to select the implementation of the InterfaceBean
// it does not support runtime switch
@IfBuildProperty(name = "at.cgsit.train.ibean", stringValue = "bean1")
@RequestScoped
public class InterfaceBeanImpl implements InterfaceBean {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("InterfaceBeanImpl echo called");
        return "InterfaceBeanImpl: " + input;
    }
}
