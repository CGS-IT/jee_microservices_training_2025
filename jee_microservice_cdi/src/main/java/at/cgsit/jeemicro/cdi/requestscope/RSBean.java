package at.cgsit.jeemicro.cdi.requestscope;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;


import java.time.LocalDateTime;

@RequestScoped
public class RSBean {

    @Inject
    Logger log;

    private String requestScopedMessage = "default";

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct called: " + LocalDateTime.now());
    }


    public String getRequestScopedMessage() {
        return requestScopedMessage;
    }

    public void setRequestScopedMessage(String requestScopedMessage) {
        this.requestScopedMessage = requestScopedMessage;
    }
}
