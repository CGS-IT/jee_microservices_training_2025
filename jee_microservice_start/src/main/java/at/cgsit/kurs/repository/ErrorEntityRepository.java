package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.ErrorEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


/**
 * used to save error entities and to dedicate produce JPA Exceptions for testing
 */
@ApplicationScoped
public class ErrorEntityRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(ErrorEntity entity) {
        em.persist(entity);
    }
}
