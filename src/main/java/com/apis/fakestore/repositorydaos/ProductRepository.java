package com.apis.fakestore.repositorydaos;

import com.apis.fakestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    List<Product> findByTitle(String title);

    List<Product> findByTitleContains(String title);
    @Override
    void delete(Product product);
    Optional<Product> findByImage(String image);
    Product save(Product product);
}
