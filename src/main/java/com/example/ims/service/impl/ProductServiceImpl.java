package com.example.ims.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ims.dto.ProductDto;
import com.example.ims.model.Product;
import com.example.ims.service.ProductService;
import com.example.ims.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        productRepository.save(product);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<ProductDto> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product) -> mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long Id) {
        productRepository.deleteById(Id);
    }
    
    private ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        
        return productDto;
    }
}
