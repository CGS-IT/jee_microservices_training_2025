package at.cgsit.kurs.repository;

import at.cgsit.kurs.model.TestEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Named;

@ApplicationScoped
public class Panache_TestEntityRepository implements PanacheRepository<TestEntity> {

    public PanacheQuery<TestEntity> findAllPaged(String name) {
        if (name != null && !name.isEmpty()) {
            return find("name = ?1", name);
        } else {
            return findAll();
        }
    }

    public long countTestEntities() {
        return count();
    }
}