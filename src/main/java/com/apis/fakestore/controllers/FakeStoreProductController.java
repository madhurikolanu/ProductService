package com.apis.fakestore.controllers;

import com.apis.fakestore.exceptions.ProductNotFoundException;
import com.apis.fakestore.models.Product;
import com.apis.fakestore.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // this controller is going to host REST HTTP API's
@RequestMapping("/fakeStoreProducts") //localhost:8080/products
public class FakeStoreProductController {

    //@Autowried latest version it is not required
    private ProductService productService;

    // at the time of injection springboot needs the object of productservice so add @service for productservice
    FakeStoreProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        if(product == null){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    //createProduct
    //deleteProduct
    //updateProduct
    //replaceProduct
}
