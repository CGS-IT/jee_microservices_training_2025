package at.cgsit.train.producer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserEventProducerTest {

    @Inject
    UserEventProducer producer;

    // Inject using @Connector
    @Inject
    @Connector("smallrye-in-memory")
    InMemoryConnector inMemoryConnector;

    @BeforeEach
    void clear() {
        inMemoryConnector.sink("user-events").clear();
    }

  @Test
  void testSendMessage() {
    producer.send("Hello");

    @SuppressWarnings("unchecked")
    List<Message<String>> messages = (List<Message<String>>) (List<?>) inMemoryConnector
        .sink("user-events")
        .received();

    assertEquals(1, messages.size());
    assertEquals("Hello", messages.get(0).getPayload());
  }

}
