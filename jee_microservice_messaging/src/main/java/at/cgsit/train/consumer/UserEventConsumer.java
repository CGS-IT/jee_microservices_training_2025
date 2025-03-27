package at.cgsit.train.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserEventConsumer {

    @Inject
    Logger logger;

    @Incoming("user-events")
    // @Incoming("internal")
    public void receive(String message) {
        logger.infov("🟢 Received event[{0}] ", message);
    }
}