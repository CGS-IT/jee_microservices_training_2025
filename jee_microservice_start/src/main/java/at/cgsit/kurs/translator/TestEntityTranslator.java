package at.cgsit.kurs.translator;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;

@ApplicationScoped
public class TestEntityTranslator {

    public TestDTO toDTO(TestEntity entity) {
        TestDTO dto = new TestDTO();
        dto.setId(entity.getId().longValue());
        dto.setVersionNumber(entity.getVersionNo());
        dto.setName(entity.getName());
        dto.setVorname("Mapped"); // or pull from another source
        //dto.setIsOk(true);
        dto.setEventDate(new Date()); // or map properly
        return dto;
    }

    public TestEntity toEntity(TestDTO dto) {
        if (dto == null) {
            return null;
        }

        TestEntity entity = new TestEntity();

        // Null-safe conversion from Long to Integer
        Long dtoId = dto.getId();
        entity.setId(dtoId != null ? dtoId.intValue() : null);

        entity.setVersionNo(dto.getVersionNumber()); // assuming it's already nullable-safe
        entity.setName(dto.getName());

        entity.setVersionNo(dto.getVersionNumber());

        return entity;
    }
}
