package at.cgsit.train.producer;

//import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserEventProducer {

    @Inject
    @Channel("user-events")
    Emitter<String> emitter;

    public void send(String message) {
        emitter.send(message);
    }
}
