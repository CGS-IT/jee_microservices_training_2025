package at.cgsit.kurs.repository;

import at.cgsit.kurs.exceptionmapper.JpaExceptionMapper;
import at.cgsit.kurs.model.ErrorEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for {@link ErrorEntityRepository}.
 * <p>
 * shows the handling of JPA errors via the {@link JpaExceptionMapper}.
 */
@QuarkusTest
class JpaErrorTest {

    @Inject
    ErrorEntityRepository repository;

    @Test
    void shouldThrowPersistenceExceptionForNullValue() {
        ErrorEntity entity = new ErrorEntity();
        entity.setNotNullValue(null); // violates NOT NULL
        entity.setUniqueValue("abc");

        assertThrows(PersistenceException.class, () -> repository.save(entity));
    }

    @Test
    void shouldThrowPersistenceExceptionForDuplicateValue() {
        ErrorEntity entity1 = new ErrorEntity();
        entity1.setNotNullValue("valid");
        entity1.setUniqueValue("duplicate");

        ErrorEntity entity2 = new ErrorEntity();
        entity2.setNotNullValue("also valid");
        entity2.setUniqueValue("duplicate"); // duplicate unique

        repository.save(entity1);

        assertThrows(PersistenceException.class, () -> repository.save(entity2));
    }
}
