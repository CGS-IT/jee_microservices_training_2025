package at.cgsit.train.model;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * shows how to test a JPA entity with Panache
 */
@QuarkusTest
class MyEntityTest {

    @Test
    @Transactional
    void testPersistAndFind() {
        // given
        MyEntity entity = new MyEntity();
        entity.field = "test-value";

        // when
        entity.persist();

        // then
        assertNotNull(entity.id);
        MyEntity found = MyEntity.findById(entity.id);
        assertNotNull(found);
        assertEquals("test-value", found.field);
    }

    @Test
    @Transactional
    void testFindAll() {
        MyEntity.deleteAll(); // clean slate

        MyEntity a = new MyEntity();
        a.field = "A";
        a.persist();

        MyEntity b = new MyEntity();
        b.field = "B";
        b.persist();

        List<MyEntity> all = MyEntity.listAll();
        assertEquals(2, all.size());
    }

    @Test
    @Transactional
    void testDelete() {
        MyEntity entity = new MyEntity();
        entity.field = "to-delete";
        entity.persist();

        Long id = entity.id;
        entity.delete();

        assertNull(MyEntity.findById(id));
    }
}
