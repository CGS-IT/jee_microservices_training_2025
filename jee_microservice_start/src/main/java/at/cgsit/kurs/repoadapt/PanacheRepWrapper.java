package at.cgsit.kurs.repoadapt;

import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.Panache_TestEntityRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("panacheRepo")
public class PanacheRepWrapper implements TestEntityRepositoryInterface {

    @Inject
    Panache_TestEntityRepository panache;

    @Override
    public List<TestEntity> findByNamePaged(String name, int page, int size) {
      PanacheQuery<TestEntity> query =
          (name != null && !name.isBlank())
              ? panache.find("name", name)
              : panache.findAll();

      return query.page(Page.of(page, size)).list();
    }

    @Override
    public long countTestEntities() {
      return panache.count();
    }

    @Override
    public Optional<TestEntity> findById(Long id) {
      return panache.findByIdOptional(id);
    }

    @Override
    public TestEntity save(TestEntity entity) {
      panache.persist(entity);
      return entity;
    }

    @Override
    public void deleteById(Long id) {
      panache.deleteById(id);
    }

  }
