package at.cgsit.kurs.startup;

import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import static at.cgsit.kurs.startup.TestNames.*;
@Singleton
public class MyStartup {

  @Inject
  Logger log;

  @Inject
  TestEntityRepository repository;

  @Transactional
  public void loadUsers(@Observes @Priority(1) StartupEvent evt) {
    log.info("MyStartup initialization called ");
    repository.deleteAll();
    repository.insertTestEntity(new TestEntity(TestNames.CHRIS.value));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK.value));
  }

}
