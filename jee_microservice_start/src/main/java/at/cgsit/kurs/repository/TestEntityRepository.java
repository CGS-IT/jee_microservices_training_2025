package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.model.TestEntity_;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;


/**
 *
 */
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

  /**
   * count Test Entities
   * @return count
   */
  public Long countTestEntities() {

    Query query = em.createQuery("select count(e) from TestEntity e");
    return (Long) query.getSingleResult();
  }

  /**
   * find all TestEntities
   * @return List<TestEntity>
   */
  public List<TestEntity> findAll() {
    return em.createQuery("SELECT e FROM TestEntity e", TestEntity.class)
        .getResultList();
  }

  /**
   * Updates an existing {@link TestEntity} in the database.
   * <p>
   * This method supports entities in the following states:
   * <ul>
   *   <li><b>Managed (attached)</b>: If the entity is already in the persistence context, it will be updated via {@code merge} safely.</li>
   *   <li><b>Detached</b>: If the entity was previously loaded and is now detached, it will be reattached and updated using {@code merge}.</li>
   *   <li><b>DTO-mapped entities</b>: New instances mapped from DTOs with a valid, existing ID will be matched and updated.</li>
   * </ul>
   * <p>
   * The method validates that:
   * <ul>
   *   <li>The entity has a non-null ID.</li>
   *   <li>An entity with the provided ID exists in the database.</li>
   * </ul>
   * If these conditions are not met, it throws an appropriate exception to prevent accidental inserts or updates of non-existent records.
   *
   * @param entity The {@code TestEntity} to be updated. Must have a valid existing ID.
   * @return The updated (and managed) entity reflecting the current state in the database.
   * @throws IllegalArgumentException if the entity has no ID.
   * @throws EntityNotFoundException if no entity exists with the given ID.
   */
  @Transactional
  public TestEntity updateTestEntity(TestEntity entity) {
    if (entity.getId() == null) {
      throw new IllegalArgumentException("Cannot update entity without ID");
    }

    TestEntity existing = em.find(TestEntity.class, entity.getId());
    if (existing == null) {
      throw new EntityNotFoundException("Entity with ID " + entity.getId() + " not found for update");
    }

    return em.merge(entity);
  }

  /**
   * delete with entity reference
   * @param entity
   */
  @Transactional
  public void deleteTestEntity(TestEntity entity) {
    if(em.contains(entity)){
      em.remove(entity);
    } else {
      this.deleteById(entity.getId());
    }


  }

  /**
   * faster delete without loading the entity first
   * @param id
   * @return
   */
  @Transactional
  public int deleteById(Long id) {

    return em.createQuery("DELETE FROM TestEntity e WHERE e.id = :id")
        .setParameter(TestEntity_.ID, id)
        .executeUpdate();
  }


  public TestEntity findTestEntity(String name) {
    throw new RuntimeException("Not implemented");
  }

  /**
   * find by id
   * @param id
   * @return
   */
  public TestEntity findById(Long id) {
    return this.readTestEntityById(id.intValue());
  }


  public TestEntity readTestEntityById(Integer id) {
    return em.find(TestEntity.class, id);
  }

  /**
   * find by name using JPQL
   * see {@link #findByNameCriteriaApi(String)} for a critera API version
   * @param name
   * @return
   */
  public List<TestEntity> findByName(String name) {

    TypedQuery<TestEntity> query = em.createQuery(
        "SELECT e FROM TestEntity e WHERE e.name = :name", TestEntity.class);

    query.setParameter(TestEntity_.NAME, name);

    List<TestEntity> resultList = query.getResultList();
    return resultList;
  }

  /**
   * Finds all {@link TestEntity} entries where the name matches,
   * using the JPA Criteria API (Query Specification API).
   *
   * @param name       The name to match
   * @param exactMatch If true, performs an exact match; if false, uses a LIKE query
   * @return List of matching {@code TestEntity} entries
   */
  public List<TestEntity> findByNameCriteriaApi(String name, boolean exactMatch) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<TestEntity> cq = cb.createQuery(TestEntity.class);
    Root<TestEntity> root = cq.from(TestEntity.class);

    Predicate condition;
    if (exactMatch) {
      condition = cb.equal(root.get(TestEntity_.NAME), name);
    } else {
      condition = cb.like(cb.lower(root.get(TestEntity_.NAME)), "%" + name.toLowerCase() + "%");
    }

    cq.select(root).where(condition);

    return em.createQuery(cq).getResultList();
  }

  /**
   * find by name with exact match or LIKE match
   * @param name
   * @param exactMatch
   * @return
   */
  public List<TestEntity> findByName(String name, boolean exactMatch) {
    String jpql = exactMatch
        ? "SELECT e FROM TestEntity e WHERE e.name = :name"
        : "SELECT e FROM TestEntity e WHERE LOWER(e.name) LIKE LOWER(:name)";

    TypedQuery<TestEntity> query = em.createQuery(jpql, TestEntity.class);

    if (exactMatch) {
      query.setParameter(TestEntity_.NAME, name);
    } else {
      query.setParameter(TestEntity_.NAME, "%" + name + "%");
    }

    return query.getResultList();
  }


}




