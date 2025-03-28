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
        TestEntity entity = new TestEntity();
        entity.setId(dto.getId().intValue()); // or null-safe
        entity.setVersionNo(dto.getVersionNumber());
        entity.setName(dto.getName());
        // etc.
        return entity;
    }
}
