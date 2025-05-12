package org.example.ecommerce_be.controller;

import org.example.ecommerce_be.dto.SizeQuantityDTO;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public List<Product> findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @GetMapping("/{id}/filterByColor")
    public List<Product> findByColor(@PathVariable Long id,@RequestParam String color){
        return productService.filterByColor(id, color);
    }

    @GetMapping("/{id}/filterByColorAndSize")
    public Product findByColor(@PathVariable Long id,@RequestParam String color, @RequestParam String size){
        return productService.filterByColorAndSize(id, color, size);
    }

    @GetMapping("/{id}/colors")
    public List<String> getDistinctColors(@PathVariable Long id) {
        return productService.getDistinctColors(id);
    }

    @GetMapping("/{id}/sizes")
    public List<SizeQuantityDTO> getDistinctSizes(@PathVariable Long id) {
        return productService.getDistinctSizes(id);
    }



}
