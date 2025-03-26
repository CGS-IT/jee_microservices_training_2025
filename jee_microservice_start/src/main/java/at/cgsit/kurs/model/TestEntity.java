package at.cgsit.kurs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
@NamedQuery(
    name = "TestEntity.findByIdWithChildren",
    query = "SELECT t FROM TestEntity t LEFT JOIN FETCH t.children WHERE t.id = :id"
)
public class TestEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Version
  @Column(name = "version_no", nullable = false)
  private Long versionNo;

  @Size(max = 600)
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Must contain only alphabetical characters")
  @Column(name = "name", length = 600)
  private String name;

  @OneToMany(
      mappedBy = "testEntity",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<ChildEntity> children = new ArrayList<>();

  public TestEntity() {
    this.name = "";
  }
  // default constructor to enable immediate object creation
  public TestEntity(String name) {
    this.name = name;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Long getVersionNo() {
    return versionNo;
  }

  public void setVersionNo(final Long versionNo) {
    this.versionNo = versionNo;
  }

  public List<ChildEntity> getChildren() {
    return children;
  }

  public void setChildren(List<ChildEntity> children) {
    this.children = children;
    for (ChildEntity child : children) {
      child.setTestEntity(this); // keep the relationship in sync
    }
  }

  public void addChild(ChildEntity child) {
    children.add(child);
    child.setTestEntity(this);
  }

  public void removeChild(ChildEntity child) {
    children.remove(child);
    child.setTestEntity(null);
  }


}