package at.cgsit.kurs.base;

import at.cgsit.kurs.data.TestNames;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logmanager.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;


/**
 * Base test class for Quarkus integration tests using {@link io.quarkus.test.junit.QuarkusTest}.
 * <p>
 * Provides shared setup and teardown logic for tests that rely on the {@link TestEntityRepository}.
 * It ensures the database is initialized to a clean, known state before tests are run.
 * </p>
 *
 * <p>Features:
 * <ul>
 *   <li>Injects {@link TestEntityRepository}</li>
 *   <li>Deletes all existing test entities before tests run</li>
 *   <li>Inserts default {@link TestEntity} records (e.g. Herr Mann, Frank Elstner)</li>
 *   <li>Provides extension points for per-test-method setup/cleanup</li>
 * </ul>
 * </p>
 *
 * <p>Subclasses must be annotated with {@code @QuarkusTest} and use {@code PER_CLASS} lifecycle:</p>
 *
 * <pre>{@code
 * @QuarkusTest
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
 * class MyTest extends BaseQuarkusTest { ... }
 * }</pre>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseQuarkusTest {

  @Inject
  protected TestEntityRepository repository;

  @BeforeAll
  @Transactional
  void initData() {
    // dbunit.cleanINsert("/data/testentity.xml");
    // we delete the test entities here, because our startup Observer will insert or default data already
    // and we need to guarantee that the data is in the same state for each test
    repository.deleteAll();
    repository.insertTestEntity(new TestEntity(TestNames.HERR_MANN.getVorname(), TestNames.HERR_MANN.getName()));
    repository.insertTestEntity(new TestEntity(TestNames.FRANK_ELSTNER.getVorname(), TestNames.FRANK_ELSTNER.getName()));
  }

  @AfterAll
  void afterAll() {
    // optional
  }


}
