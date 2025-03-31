package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.DatatypesEntity;
import at.cgsit.kurs.model.testdata.DatatypesEntityFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DatatypesEntityTest {

    @Inject
    DatatypesEntityRepository repository;

    @Test
    @Transactional
    void shouldPersistAndFetchDatatypesEntity() {
        // given
        DatatypesEntity entity = DatatypesEntityFactory.createDefault();

        // when
        repository.save(entity);
        Long id = entity.getId();

        // then
        DatatypesEntity fetched = repository.findById(id);
        assertNotNull(fetched);
        assertEquals(entity.getStringValue(), fetched.getStringValue());
        assertEquals(entity.getWrapperInt(), fetched.getWrapperInt());
        assertEquals(entity.getEnumValue(), fetched.getEnumValue());
        assertEquals(entity.getUuid(), fetched.getUuid());
        assertEquals(entity.getBigDecimal(), fetched.getBigDecimal());
    }
}
