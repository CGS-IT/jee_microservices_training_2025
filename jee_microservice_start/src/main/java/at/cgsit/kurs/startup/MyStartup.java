package at.cgsit.kurs.startup;

import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

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
    repository.insertTestEntity(new TestEntity(TestNames.HERR_MANN.getVorname(), TestNames.HERR_MANN.getName()));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK_ELSTNER.getVorname(), TestNames.FRANK_ELSTNER.getName()));
  }

}
