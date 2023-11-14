package com.example.ims.controller;

import com.example.ims.dto.ProductDto;
import com.example.ims.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDto productDto) {
        productService.saveProduct(productDto);
        return "redirect:/home"; // redirect to home
    }

    
    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10; // Number of products per page
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<ProductDto> productPage = productService.findAllProduct(pageable);
        List<ProductDto> products = productPage.getContent();

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "home";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/home"; // redirect to home
    }

    @GetMapping("/editProduct/{productId}")
    public String editProduct(@PathVariable Long productId, Model model) {
        ProductDto productDto = productService.findProductById(productId);
    
        model.addAttribute("product", productDto);
    
        return "editProduct";
    }
    

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute ProductDto productDto) {
        productService.updateProduct(productDto);
        return "redirect:/home"; // redirect to home
    }
    
    @PostMapping("/search")
    public String search(@RequestParam String searchInput, Model model) {
        List<ProductDto> searchResults = productService.searchProducts(searchInput);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
