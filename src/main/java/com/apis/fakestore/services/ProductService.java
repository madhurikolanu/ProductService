package com.apis.fakestore.services;

import com.apis.fakestore.exceptions.ProductNotFoundException;
import com.apis.fakestore.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);

}
