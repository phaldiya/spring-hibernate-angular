package com.learn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.domain.conf.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "Core")
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    @NotNull
    private String firstName;

    @Column(length = 20)
    private String middleName;

    @Column(length = 50)
    @NotNull
    private String lastName;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String title;

    @Transient
    @JsonProperty(value = "$typeahead")
    public String getName() {
        StringBuilder sb = new StringBuilder();
        if (getLastName() != null) {
            sb.append(getLastName().trim());
        }
        if (getFirstName() != null) {
            sb.append(", ");
            sb.append(getFirstName().trim());
        }
        return sb.toString();
    }

    public Person() {
        super();
    }

    public Person(Integer id, String firstName, String lastName) {
        super();
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(Integer id, String firstName, String lastName, String email) {
        this(id, firstName, lastName);
        this.email = email;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
