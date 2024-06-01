package com.apis.fakestore.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String title;
    private Long id;
    private String description;
    private double price;
    private String image;
    private String category;
}
