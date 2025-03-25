package at.cgst.kurs.repository;

import at.cgst.kurs.model.TestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

import java.util.List;


@ApplicationScoped
public class TestEntityRepository {

  @Inject
  EntityManager em;

  // create insert method
  @Transactional()
  public TestEntity insertTestEntity(TestEntity entity) {
    em.persist(entity);

    return entity;
  }

  public Long countTestEntities() {

    Query query = em.createQuery("select count(e) from TestEntity e");

    return (Long) query.getSingleResult();

  }

  /**
   * Updates an existing TestEntity
   *
   * <p>This method handles both managed and detached entities:
   * - If the entity is already managed by the persistence context, it flushes changes to the database.
   * - If the entity is detached, it reattaches and updates it using {@code em.merge()}.
   *
   * <p>The returned entity reflects the current state of the database, including any updated fields such as version numbers.
   *
   * @param entity The TestEntity to be updated. It may be managed or detached.
   * @return The updated TestEntity with the latest database state.
   */
  @Transactional
  public TestEntity updateTestEntity(TestEntity entity) {
    if (em.contains(entity)) {
      em.flush(); // flush to DB only .. so work is done for commit
      return entity;
    } else {
      return em.merge(entity); // Reattaches and updates the detached entity
    }
  }


  public TestEntity findTestEntity(String name) {
    throw new RuntimeException("Not implemented");
  }
  public TestEntity findById(Long id) {
    return this.readTestEntityById(id.intValue());
  }

  public TestEntity readTestEntityById(Integer id) {
    return em.find(TestEntity.class, id);
  }

  public <T> List<T> findAll(Class<T> entityClass) {
    return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e ", entityClass)
        .getResultList();
  }

}




