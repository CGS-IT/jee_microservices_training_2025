package at.cgsit.kurs.model;

import jakarta.persistence.*;


/**
 * Entity class for testing error handling.
 * <br>
 * UniqueConstraint Use this when: <br/>
 * You need to define a composite unique constraint (e.g. on two columns)<br/>
 * Or you prefer centralized schema constraints at table level<br/>
 */
@Entity
@Table(name = "jpa_error_entity", uniqueConstraints = @UniqueConstraint(columnNames = "uniqueValue"))
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String notNullValue;

    @Column(unique = true)
    private String uniqueValue;

    public ErrorEntity() {
    }

    public ErrorEntity(String initialValue, String unique123) {
        this.notNullValue = initialValue;
        this.uniqueValue = unique123;
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getNotNullValue() { return notNullValue; }
    public void setNotNullValue(String notNullValue) { this.notNullValue = notNullValue; }

    public String getUniqueValue() { return uniqueValue; }
    public void setUniqueValue(String uniqueValue) { this.uniqueValue = uniqueValue; }
}
