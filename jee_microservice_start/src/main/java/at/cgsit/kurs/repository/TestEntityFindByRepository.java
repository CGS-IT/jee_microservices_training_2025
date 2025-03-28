package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.model.TestEntity_;
import at.cgsit.kurs.repoadapt.TestEntityRepositoryInterface;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * this is the alterniative implementation of the TestEntityRepository
 * native without panache
 */
@ApplicationScoped
@Named("jpaRepo")
public class TestEntityFindByRepository implements TestEntityRepositoryInterface {

    @Inject
    EntityManager em;

    public List<TestEntity> findByNamePaged(String name, int page, int size) {
        String jpql = "SELECT t FROM TestEntity t";
        boolean hasName = name != null && !name.trim().isEmpty();

        if (hasName) {
            jpql += " WHERE t.name = :name";
        }

        TypedQuery<TestEntity> query = em.createQuery(jpql, TestEntity.class);
        if (hasName) {
            query.setParameter(TestEntity_.NAME, name);
        }

        return query
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public long countTestEntities() {
        return em.createQuery("SELECT COUNT(t) FROM TestEntity t", Long.class)
                 .getSingleResult();
    }

    public Optional<TestEntity> findById(Integer id) {
        return Optional.ofNullable(em.find(TestEntity.class, id));
    }

    public TestEntity save(TestEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    public void deleteById(Integer id) {
        findById(id).ifPresent(em::remove);
    }
}
