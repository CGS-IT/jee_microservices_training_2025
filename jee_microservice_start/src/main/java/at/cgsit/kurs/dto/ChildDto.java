package at.cgsit.kurs.dto;

import at.cgsit.kurs.model.TestEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Instant;

/**
 *
 */
public class ChildDto {

    @JsonProperty(value = "id", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "-1")
    private Long id;

    @JsonProperty(value = "child_name", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "nameDefault")
    private String childName;

    @JsonProperty(value = "created_at", required = false, access = JsonProperty.Access.READ_WRITE)
    private Instant createdAt = Instant.now(); // default to now

    @JsonProperty(value = "parent_id", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "-1")
    private Long parentId;

    public ChildDto() {
    }

    public ChildDto(Long id, String childName, Instant createdAt, Long parentId) {
        this.id = id;
        this.childName = childName;
        this.createdAt = createdAt;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
