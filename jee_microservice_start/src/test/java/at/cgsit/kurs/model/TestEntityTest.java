package at.cgsit.kurs.model;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TestEntityTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void testPersistAndLoadTestEntity() {
        TestEntity entity = new TestEntity();
        entity.setName("Smith");
        entity.setVorname("John");
        entity.setActive(true);
        Date now = new Date();
        entity.setEventDate(now);

        ChildEntity child1 = new ChildEntity("Child One", entity);
        ChildEntity child2 = new ChildEntity("Child Two", entity);
        entity.setChildren(Arrays.asList(child1, child2));

        em.persist(entity);
        em.flush();
        em.clear();

        TestEntity loaded = em.find(TestEntity.class, entity.getId());
        assertNotNull(loaded);
        assertEquals("Smith", loaded.getName());
        assertEquals("John", loaded.getVorname());
        assertTrue(loaded.isActive());
        assertEquals(now, loaded.getEventDate());

        assertNotNull(loaded.getChildren());
        assertEquals(2, loaded.getChildren().size());
        assertTrue(
            loaded.getChildren().stream()
                  .anyMatch(c -> c.getChildName().equals("Child One")));

        String asString = loaded.toString();
        assertTrue(asString.contains("Smith"));
        assertTrue(asString.contains("John"));
        assertTrue(asString.contains("Child One"));
    }
}
