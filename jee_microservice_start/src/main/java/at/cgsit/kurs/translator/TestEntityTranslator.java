package at.cgsit.kurs.translator;

import at.cgsit.kurs.dto.TestDTO;
import at.cgsit.kurs.model.TestEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.time.Clock;

@ApplicationScoped
public class TestEntityTranslator {

    public TestDTO toDTO(TestEntity entity) {
        if (entity == null) return null;

        TestDTO dto = new TestDTO();
        dto.setId(entity.getId() != null ? entity.getId() : null);
        dto.setVersionNumber(entity.getVersionNo());
        dto.setName(entity.getName());
        dto.setVorname(entity.getVorname());
        dto.setEventDate(entity.getEventDate());
        dto.setActive(entity.isActive());
        return dto;
    }

    public TestEntity toEntity(TestDTO dto) {
        if (dto == null) return null;

        TestEntity entity = new TestEntity();
        entity.setId(dto.getId() != null ? dto.getId() : null);
        entity.setVersionNo(dto.getVersionNumber());
        entity.setName(dto.getName());
        entity.setVorname(dto.getVorname());
        entity.setEventDate(dto.getEventDate());
        entity.setActive(dto.isActive());
        return entity;
    }
}
