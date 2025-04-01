package at.cgsit.kurs.repoadapt;

import at.cgsit.kurs.model.TestEntity;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for TestEntity to switch between different implementations.
 */
public interface TestEntityRepositoryInterface {

    List<TestEntity> findByNamePaged(String name, int page, int size);

    long countTestEntities();
    Optional<TestEntity> findById(Long id);

    TestEntity save(TestEntity entity);

    void deleteById(Long id);

}