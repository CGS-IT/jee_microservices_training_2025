package at.cgsit.train.producer;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserEventProducer {

    @Inject
    @Channel("user-events")
    @Broadcast // Tell Quarkus explicitly that your Emitter can send to multiple consumers
    Emitter<String> emitter;

    public void send(String message) {
        emitter.send(message);
    }
}
