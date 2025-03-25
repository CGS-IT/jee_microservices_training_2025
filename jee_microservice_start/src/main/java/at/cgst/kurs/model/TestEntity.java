package at.cgst.kurs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "test")
public class TestEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Version
  @Column(name = "version_no", nullable = false)
  private Long versionNo;

  @Size(max = 600)
  // @Pattern(regexp = "^[a-zA-Z]+$", message = "Must contain only alphabetical characters")
  @Column(name = "name", length = 600)
  private String name;

  public Long getId() {
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

}