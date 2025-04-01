package at.cgsit.train.consumer;

import at.cgsit.train.producer.UserEventProducer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@QuarkusTest
class UserEventConsumerTest {

    @Inject
    UserEventProducer producer;

    @InjectSpy
    UserEventConsumer consumer;

    @Inject
    @Channel("user-events")
    @Broadcast
    Emitter<String> testEmitter;

    @Test
    void testMessageIsConsumed_withSpyOnConsumerAndLogger() {
        String testMessage = "Hello Consumer!";
        testEmitter.send(testMessage);

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(consumer, times(1)).receive(testMessage);
            // verify(logger, times(1)).infov("Received event[{0}] ", testMessage);
        });
    }
}
