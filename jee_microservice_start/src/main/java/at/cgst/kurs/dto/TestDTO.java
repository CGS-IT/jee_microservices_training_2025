package at.cgst.kurs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({ "id", "versionNumber", "name", "vorname" })
public class TestDTO {

  @JsonProperty(value = "id", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "-1")
  private Long id;

  @JsonProperty(value = "versionNumber", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "0")
  private Long versionNumber;

  @JsonProperty(value = "name", required = true, access = JsonProperty.Access.READ_WRITE, defaultValue = "nameDefault")
  String name;

  @JsonProperty(value = "vorname", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "vornameDefault")
  String vorname;

  @JsonProperty(value = "isOk", required = false, access = JsonProperty.Access.READ_WRITE, defaultValue = "true")
  Boolean isOk;

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

  public Boolean getOk() {
    return isOk;
  }

  public void setOk(Boolean ok) {
    isOk = ok;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
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
        ", isOk=" + isOk +
        ", eventDate=" + eventDate +
        '}';
  }

}