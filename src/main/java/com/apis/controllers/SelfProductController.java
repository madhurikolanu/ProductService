package com.apis.controllers;

import com.apis.commons.AuthCommons;
import com.apis.dtos.UserDto;
import com.apis.exceptions.ProductNotFoundException;
import com.apis.models.Product;
import com.apis.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // this controller is going to host REST HTTP API's
@RequestMapping("/products") //localhost:8080/products
public class SelfProductController {

    //@Autowried latest version it is not required
    private ProductService productService;
    private AuthCommons authCommons;

    // at the time of injection springboot needs the object of productservice so add @service for productservice
    SelfProductController(@Qualifier("selfProductService") ProductService productService, AuthCommons authCommons){
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {

        // below user related code is a manually generating token to connect to userservice
       /* UserDto userDto = authCommons.validateToken(auth);
        ResponseEntity<Product> responseEntity;
        if(userDto == null){
            responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return responseEntity;
        }*/
        //further required filter based on roles
        ResponseEntity<Product> responseEntity;
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isEmpty()){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        /*ResponseEntity<Product> responseEntity;
        if(product == null){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);*/
        return responseEntity;
    }

    @GetMapping("/")
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                        @RequestParam("pageSize") int pageSize){

        return productService.getAllProducts(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    //createProduct
    //deleteProduct
    //updateProduct
    //replaceProduct

    @PostMapping("/create")
   public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
