package at.cgsit.kurs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
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
  private Long id;

  @Version
  @Column(name = "version_no", nullable = false)
  private Long versionNo;

  @Size(max = 600)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Must contain only alphabetical characters and space characters")
  @Column(name = "name", length = 600)
  private String name;

  @Column(name = "vorname", length = 250, nullable = true)
  String vorname;

  @Column(name = "active")
  private Boolean active;

  //  when using java.util.Date in JPA, you should always add @Temporal
  //  to tell JPA how to store it (DATE, TIME, or TIMESTAMP),
  //  since Date represents both date and time in Java.
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "event_date")
  private Date eventDate;

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
  public TestEntity(String vorname, String name) {
    this.vorname = vorname;
    this.name = name;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean ok) {
    active = ok;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  @Override
  public String toString() {
    return "TestEntity{" +
        "id=" + id +
        ", versionNo=" + versionNo +
        ", name='" + name + '\'' +
        ", vorname='" + vorname + '\'' +
        ", active=" + active +
        ", eventDate=" + eventDate +
        ", children=" + children +
        '}';
  }
}