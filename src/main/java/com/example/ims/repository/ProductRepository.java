package com.example.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ims.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
