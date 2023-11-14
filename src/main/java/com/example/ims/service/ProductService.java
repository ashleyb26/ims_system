package com.example.ims.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.ims.dto.ProductDto;
import com.example.ims.model.Product;
import com.example.ims.repository.ProductRepository;

import org.springframework.data.domain.Pageable;


@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Page<ProductDto> findAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::mapToProductDto);
    }

    public void saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        productRepository.save(product);
    }

    public List<ProductDto> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product) -> mapToProductDto(product))
                .collect(Collectors.toList());
    }

    public void deleteProductById(Long Id) {
        productRepository.deleteById(Id);
    }
    
    private ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        
        return productDto;
    }

    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return mapToProductDto(product);
    }

    public void updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(productDto.getName());
            existingProduct.setQuantity(productDto.getQuantity());
            existingProduct.setPrice(productDto.getPrice());
            productRepository.save(existingProduct);
        }
    }

    public List<ProductDto> searchResults(String searchInput) {
        List<Product> searchProducts = productRepository.findByNameContainingIgnoreCase(searchInput);
        return searchProducts.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}