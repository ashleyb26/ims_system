package com.example.ims.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ims.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
