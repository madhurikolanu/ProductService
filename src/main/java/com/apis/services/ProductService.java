package com.apis.services;

import com.apis.exceptions.ProductNotFoundException;
import com.apis.models.Product;
import com.apis.projections.ProductWithTitleDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Optional<Product> getProductById(Long id) throws ProductNotFoundException;
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

    Page<Product> getAllProducts(int pageNumber, int pageSize);
}
