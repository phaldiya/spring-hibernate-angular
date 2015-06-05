package com.learn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learn.domain.conf.BaseEntity;
import com.learn.domain.serializer.CategorySimpleSerializer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by priyaan-pc on 6/3/2015.
 */
@Entity
@Table(schema = "core")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @NotNull
    private String productName;

    @NotNull
    private double price;

    private String prodeuctDescription;

    @ManyToOne
    private Category category;

    @JsonProperty
    public int getProductId() {
        return productId;
    }

    @JsonProperty
    public String getProductName() {
        return productName;
    }

    @JsonProperty
    public double getPrice() {
        return price;
    }

    @JsonProperty
    public String getProdeuctDescription() {
        return prodeuctDescription;
    }

    @JsonSerialize(using = CategorySimpleSerializer.class)
    public Category getCategory() {
        return category;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProdeuctDescription(String prodeuctDescription) {
        this.prodeuctDescription = prodeuctDescription;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


