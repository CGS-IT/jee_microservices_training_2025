package at.cgsit.kurs.model;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Test entity for JPA testing.
 * when loading a child you can use the following query to fetch the parent entity:
 * <br>
 * @Query("SELECT c FROM ChildEntity c JOIN FETCH c.testEntity WHERE c.id = :id")
 */
@Entity
@Table(name = "child_entity")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "child_name", nullable = false)
    private String childName;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now(); // default to now

    @ManyToOne
    @JoinColumn(name = "test_entity_id", nullable = false)
    private TestEntity testEntity;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public ChildEntity() {
    }

    public ChildEntity(String childName, TestEntity parent) {
        this.childName = childName;
        this.setTestEntity(parent);
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

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;

        // Maintain bidirectional consistency
        if (testEntity != null && !testEntity.getChildren().contains(this)) {
            testEntity.getChildren().add(this);
        }
    }

}
