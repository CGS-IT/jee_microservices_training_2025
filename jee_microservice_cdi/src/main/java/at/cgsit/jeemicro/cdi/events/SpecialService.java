package at.cgsit.jeemicro.cdi.events;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

import java.util.concurrent.CompletionStage;

@Singleton
public class SpecialService {

    @Inject
    Logger log;

    @Inject
    Event<SpecialEvent> event;

    public void doSomething() {
        log.info("SpecialService called before event.fire");

        event.fire(new SpecialEvent("Special Event"));

        CompletionStage<SpecialEvent> specialMessage = event.fireAsync(new SpecialEvent("Special message"));
        // TODO my work
        // wait for asynchonos processing to be completed if required
        // SpecialEvent join = specialMessage.toCompletableFuture().join();

        log.info("SpecialService called AFTER event");
    }
}
