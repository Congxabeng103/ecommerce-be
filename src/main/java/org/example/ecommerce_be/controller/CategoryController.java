package org.example.ecommerce_be.controller;


import org.example.ecommerce_be.entity.Category;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.service.CategoryService;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    @Autowired
    private CategoryService categoryService ;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

}