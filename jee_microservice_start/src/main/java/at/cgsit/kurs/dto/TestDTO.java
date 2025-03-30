package at.cgsit.kurs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@JsonPropertyOrder({ "id", "versionNumber", "name", "vorname" })
public class TestDTO {

  @JsonProperty(value = "id", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "-1")
  private Long id;

  @JsonProperty(value = "versionNumber", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "0")
  private Long versionNumber;

  /**
   * TIP - shows bean validation annotations
   * <br>
   * <li>- @Pattern: The annotated element must match the specified regular expression.
   * <li>- @NotEmpty: The annotated element must not be null nor empty.
   * <br>
   * if you use this and validate the object in the Rest-API Controller/Resource you can avoid<br/>
   * manual validation in the controller method itself.
   *
   */
  @JsonProperty(value = "name", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "nameDefault")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Must contain only alphabetical characters")
  @NotEmpty
  String name;

  @JsonProperty(value = "vorname", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "vornameDefault")
  String vorname;


  /**
   * TIP
   * Boolean Field Naming Convention:
   * <p>
   * The JavaBeans convention expects boolean getters to follow these rules:
   * <ul>
   *   <li><code>isOk()</code> if the field is named <code>ok</code></li>
   *   <li><code>getOk()</code> if the field is not a primitive <code>boolean</code></li>
   * </ul>
   * <p>
   * If your field is named <code>isOk</code> or <code>isActive</code>, you may end up with:
   * <ul>
   *   <li><code>isIsOk()</code> &rarr; awkward and redundant</li>
   *   <li><code>getIsOk()</code> &rarr; violates the boolean getter convention</li>
   * </ul>
   */
  @JsonProperty(value = "active", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "true")
  Boolean active;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date eventDate;

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

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }


  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(Long versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public String toString() {
    return "TestDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", vorname='" + vorname + '\'' +
        ", active=" + active +
        ", eventDate=" + eventDate +
        '}';
  }



}