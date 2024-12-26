package com.apis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
// this is for elasticsearch
@Document(indexName = "products")
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne
    @JoinColumn
    private Category category;
    private int quantity;
}
