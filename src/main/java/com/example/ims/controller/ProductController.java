package com.example.ims.controller;

import com.example.ims.dto.ProductDto;
// import com.example.ims.model.Product;
import com.example.ims.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
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
        return "redirect:/home"; // Redirect to the home page after adding a product
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductDto> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long Id) {
        productService.deleteProductById(Id);
        return "redirect:/home"; // Redirect to the home page after deleting a product
    }

}
