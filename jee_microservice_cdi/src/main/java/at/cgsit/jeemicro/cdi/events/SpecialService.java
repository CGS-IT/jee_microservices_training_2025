package at.cgsit.jeemicro.cdi.events;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class SpecialService {

    @Inject
    Logger log;

    @Inject
    Event<SpecialEvent> event;

    public void doSomething() {
        log.info("SpecialService called before event.fire");
        event.fire(new SpecialEvent("Special Event"));
        log.info("SpecialService called AFTER event");
    }
}
