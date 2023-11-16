package com.example.ims.controller;

import com.example.ims.dto.ProductDto;
import com.example.ims.model.Category;
import com.example.ims.service.CategoryService;
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
import org.springframework.data.domain.Sort;


@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService; // Add CategoryService

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDto productDto) {
        productService.saveProduct(productDto);
        return "redirect:/home"; // redirect to home
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "categoryFilter", required = false) Long categoryId, Model model) {

        if (categoryId != null) {
            if (categoryId.equals(0L)) {
                // If "All Categories" is selected, redirect to home
                return "redirect:/home";
            } else {
                // Handle category-based search
                List<ProductDto> searchProducts = productService.findProductsByCategory(categoryId);
                model.addAttribute("searchProducts", searchProducts);
            }
        }

        // Add categories for dropdown
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "searchProducts";
    }
    

    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "name") String sortBy) {
        // Fetch categories and add them to the model
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        int pageSize = 10; // Number of products per page
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));

        Page<ProductDto> productPage = productService.findAllProduct(pageable);
        List<ProductDto> products = productPage.getContent();

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);

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
    
    @GetMapping("/productsByCategory/{categoryId}")
    public String productsByCategory(@PathVariable Long categoryId, Model model) {
        List<ProductDto> products = productService.findProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "productsByCategory";
    }
}
