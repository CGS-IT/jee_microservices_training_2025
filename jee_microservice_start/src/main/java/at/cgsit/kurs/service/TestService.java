package at.cgsit.kurs.service;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.Panache_TestEntityRepository;
import at.cgsit.kurs.repository.TestEntityRepository;
import at.cgsit.kurs.translator.TestEntityTranslator;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * pure panache usage service simple example
 * uses the panahe query here in the service layer to page and finalize and tranlate the result
 */
@ApplicationScoped
public class TestService {

    @Inject
    TestEntityTranslator translator;

    @Inject
    Validator validator;

    @Inject
    Panache_TestEntityRepository testRepo;

    /**
     * Find all tests my name with paging
     * @return ist<TestDTO>
     */
    public List<TestDTO> findByNamePaged(String name, int page, int size) {
        return testRepo.findAllPaged(name)
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(translator::toDTO)
                .collect(Collectors.toList());
    }

    // check if this needs a transaction ?
    // if thrown, see if jaxrs exception mapper can handle it
    public void create(TestDTO dto) {

        validate(dto); // 🛡️ manually validate the DTO

        TestEntity entity = translator.toEntity(dto);
        testRepo.persist(entity);
    }

    private <T> void validate(T obj) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", new HashSet<>(violations));
        }
    }


}