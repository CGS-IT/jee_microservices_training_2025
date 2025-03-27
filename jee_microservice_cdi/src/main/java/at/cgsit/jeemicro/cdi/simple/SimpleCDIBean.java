
package at.cgsit.jeemicro.cdi.simple;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@RequestScoped
public class SimpleCDIBean {

    @Inject
    Logger log;

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct called ");
    }

    public String echo(String input) {
        log.info("SimpleCDIBean");
        return echoReverse(input);
    }

    public String echoReverse(String input) {
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString();
    }
}
