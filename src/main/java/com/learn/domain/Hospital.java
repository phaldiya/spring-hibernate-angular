package com.learn.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.domain.conf.BaseEntity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 * Created by priyaan-pc on 5/18/2015.
 */
@Entity
@Table(schema = "Core")
public class Hospital extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    @NotNull
    private String hospitalName;

    @Column(length = 30)
    @NotNull
    private String hospitalCity;
@JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    @JsonProperty
    public String getHospitalCity() {
        return hospitalCity;
    }

    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }


}
