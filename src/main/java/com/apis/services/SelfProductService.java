package com.apis.services;

import com.apis.exceptions.ProductNotFoundException;
import com.apis.models.Product;
import com.apis.projections.ProductWithTitleDescription;
import com.apis.repositorydaos.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
//@Primary - as we have two prod service implemnetaions controller doesnt understand which one to pick but if we give primary then it will consider this one only
// otherwise we can mention service name here and use the same in controller
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(Long id) throws ProductNotFoundException {
        //fetch the product with given id from db
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public ProductWithTitleDescription someRandomQuery(Long id) {
        return null;
    }

    @Override
    public ProductWithTitleDescription sqlQuery(Long id) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize,
                Sort.by("price").ascending().and(Sort.by("title").ascending())));
    }
}
