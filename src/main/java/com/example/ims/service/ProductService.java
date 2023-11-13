package com.example.ims.service;

import com.example.ims.dto.ProductDto;
import com.example.ims.model.Product;


import java.util.List;

public interface ProductService {

    void saveProduct(ProductDto productDto);

    Product findProductByName(String name);

    List<ProductDto> findAllProduct();

    void deleteProductById(Long Id);
}
