package org.example.ecommerce_be.controller;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public List<Product> findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @GetMapping("/{id}/filter")
    public List<Product> findByColor(@PathVariable Long id,@RequestParam(required = false) String color){
        return productService.findByColor(id,color);
    }



}
