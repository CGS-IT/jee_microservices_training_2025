package at.cgsit.jeemicro.cdi.events;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SpecialEventListener {

    @Inject
    Logger log;

    void onSpecialEventCompleted(@Observes SpecialEvent event) {
        log.info("SpecialEventListener called: " + event.getMessage());
    }

    void onSpecialEventCompletedAsynchronous(@ObservesAsync SpecialEvent event) {
        log.info("SpecialEventListener ObservesAsync called: " + event.getMessage());
    }

}
