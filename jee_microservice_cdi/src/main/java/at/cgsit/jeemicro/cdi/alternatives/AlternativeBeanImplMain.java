package at.cgsit.jeemicro.cdi.alternatives;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.Serializable;

@RequestScoped
public class AlternativeBeanImplMain implements AlternativeBeanInterface {
    @Inject
    Logger log;

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct called ");
    }

    public String echo(String input) {
        log.info("TestBeanImplA");
        return echoReverse(input);
    }

    public String echoReverse(String input) {
        log.info("TestBeanImplA");
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString();
    }



}
