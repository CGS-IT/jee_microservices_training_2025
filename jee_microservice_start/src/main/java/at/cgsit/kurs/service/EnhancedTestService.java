package at.cgsit.kurs.service;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repoadapt.PanacheRepWrapper;
import at.cgsit.kurs.repoadapt.TestEntityRepositoryInterface;
import at.cgsit.kurs.repository.TestEntityFindByRepository;
import at.cgsit.kurs.resource.TestResource;
import at.cgsit.kurs.translator.TestEntityTranslator;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnhancedTestService {
  private static final Logger LOG = Logger.getLogger(EnhancedTestService.class);

  @Inject
  TestEntityTranslator translator;

  @Inject
  Validator validator;

  @Inject
  @Named("jpaRepo")
  TestEntityFindByRepository jpaRepo;

  @Inject
  @Named("panacheRepo")
  PanacheRepWrapper panacheRepo;

  @ConfigProperty(name = "at.cgs.training.whichrepo")
  String selectedRepo;

  private TestEntityRepositoryInterface getActiveRepo() {
    TestEntityRepositoryInterface interf = "panache".equalsIgnoreCase(selectedRepo) ? panacheRepo : jpaRepo;
    LOG.infov("Using {0} repository", interf.getClass().getSimpleName());
    return interf;
  }

  public List<TestDTO> findByNamePaged(String name, int page, int size) {
    return getActiveRepo().findByNamePaged(name, page, size)
        .stream()
        .map(translator::toDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public TestDTO create(TestDTO dto) {

    validate(dto); // 🛡️ manually validate the DTO

    TestEntity entity = translator.toEntity(dto);
    entity = getActiveRepo().save(entity);
    return translator.toDTO(entity);
  }

  /**
   * validation as a responsibitly for service layer
   * call it and throw exception if not valid
   * @param obj
   * @param <T>
   */
  private <T> void validate(T obj) {
    Set<ConstraintViolation<T>> violations = validator.validate(obj);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException("Validation failed", new HashSet<>(violations));
    }
  }


}
