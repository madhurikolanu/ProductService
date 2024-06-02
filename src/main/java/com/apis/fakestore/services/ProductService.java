package com.apis.fakestore.services;

import com.apis.fakestore.exceptions.ProductNotFoundException;
import com.apis.fakestore.models.Product;
import com.apis.fakestore.projections.ProductWithTitleDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Product createProduct(Product product);
    void deleteProduct();

    //HQL - no need to remember tablename use model name
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductWithTitleDescription someRandomQuery(@Param("id") Long id);

    // this query returns only title and description so create an interface contains getters of those attributes then it will work
    // alias is also important here - alias should match the getternames


    //SQL - exactly match table name and attributes names
    // also called as native queries
    @Query(value = "select p.title, p.description from product as p where p.id = :id")
    ProductWithTitleDescription sqlQuery(@Param("id") Long id);
}
