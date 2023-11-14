package com.example.ims.controller;

import com.example.ims.dto.ProductDto;
import com.example.ims.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public String home(Model model) {
        List<ProductDto> products = productService.findAllProduct();
        model.addAttribute("products", products);
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
    

}
