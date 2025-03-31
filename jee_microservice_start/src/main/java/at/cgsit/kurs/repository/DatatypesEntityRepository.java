package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.DatatypesEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DatatypesEntityRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(DatatypesEntity entity) {
        em.persist(entity);
    }

    public DatatypesEntity findById(Long id) {
        return em.find(DatatypesEntity.class, id);
    }
}
