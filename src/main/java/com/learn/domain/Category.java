package com.learn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(schema = "core")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    @NotNull
    private String name;

    private String discription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Product> product = new ArrayList<Product>();

    @JsonProperty
    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}

