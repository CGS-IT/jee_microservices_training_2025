package at.cgsit.kurs.model;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
class ChildEntityTest {

    @Inject
    EntityManager em;

    @Test
    void testChildEntityPersistenceAndRelation() {
        // Create and persist parent
        TestEntity parent = new TestEntity();
        parent.setName("Parent");
        parent.setVersionNo(1L);
        em.persist(parent);

        // Create and persist child
        ChildEntity child = new ChildEntity();
        child.setChildName("ChildOne");
        child.setTestEntity(parent);
        em.persist(child);
        em.flush();

        // Verify child was persisted
        assertNotNull(child.getId());

        // Verify parent-child relationship using query
        TypedQuery<ChildEntity> query = em.createQuery(
                "SELECT c FROM ChildEntity c JOIN FETCH c.testEntity WHERE c.id = :id", ChildEntity.class);
        query.setParameter("id", child.getId());
        ChildEntity result = query.getSingleResult();

        assertEquals("ChildOne", result.getChildName());
        assertEquals("Parent", result.getTestEntity().getName());
        assertNotNull(result.getCreatedAt());
        assertTrue(result.getCreatedAt().isBefore(Instant.now().plusSeconds(1)));
    }

    @Test
    void testMultipleChildrenPersisted() {
        TestEntity parent = new TestEntity();
        parent.setName("ParentTwo");
        parent.setVersionNo(1L);
        em.persist(parent);

        ChildEntity child1 = new ChildEntity("One", parent);
        ChildEntity child2 = new ChildEntity("Two", parent);
        em.persist(child1);
        em.persist(child2);
        em.flush();

        TypedQuery<ChildEntity> query = em.createQuery(
                "SELECT c FROM ChildEntity c WHERE c.testEntity.id = :parentId", ChildEntity.class);
        query.setParameter("parentId", parent.getId());
        List<ChildEntity> children = query.getResultList();

        assertEquals(2, children.size());
    }
}