package com.apis.controllers.fakestore;

import com.apis.configs.RestTemplateConfig;
import com.apis.dtos.UserDto;
import com.apis.exceptions.ProductNotFoundException;
import com.apis.models.Product;
import com.apis.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController // this controller is going to host REST HTTP API's
@RequestMapping("/fakeStoreProducts") //localhost:8080/products
public class FakeStoreProductController {

    //@Autowried latest version it is not required
    private ProductService productService;
    private RestTemplate restTemplate;

    // at the time of injection springboot needs the object of productservice so add @service for productservice
    FakeStoreProductController(@Qualifier("fakeStoreService") ProductService productService, RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {

        // In general we will give complete url like http://localhost:8181/users/1 but as we have integrated service
        // discovery and this user service acts as client we will use user service application name registered in
        // service discovery and making this resttemplate as load balanced it will take care of which instance we need
        // to invoked from the list of instances received from servicediscovery
        // here we are connecting to microservice user service without any gateway directly connecting to user service via service discovery and @Loadbalanced annotation
        /*UserDto userDto = restTemplate.getForObject("http://UserService/users/1", UserDto.class);
        if(userDto == null){

        }*/
        //commenting the above code as we are calling this api via API gateway
        // which has load balancer inmplemented in it


        // we can directly connect two microservices via service discovery and @load balanced annotation
        // we can connect two microservices via API gateway which also mantains loadbalancer



        Optional<Product> product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        if(product == null){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product.get(), HttpStatus.OK);
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
