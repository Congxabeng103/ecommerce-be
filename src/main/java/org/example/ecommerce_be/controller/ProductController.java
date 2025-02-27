package org.example.ecommerce_be.controller;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProductsPageable(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productService.getAllProductsPageable(currentPage, pageSize);
    }
    @GetMapping("/{id}")
    public Optional<Product> findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
    @GetMapping("/sort-price-asc")
    public Page<Product> sortProductByPriceAsc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productService.sortProductByPriceAsc(currentPage, pageSize);
    }
    @GetMapping("/sort-price-desc")
    public Page<Product> sortProductByPriceDesc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productService.sortProductByPriceDesc(currentPage, pageSize);
    }
    @GetMapping("/filter")
    public Page<Product> getFilteredProducts(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) List<Long> category,
            @RequestParam(required = false) Long color,
            @RequestParam(required = false) Long size,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return productService.getFilteredProducts(currentPage,pageSize,category, color, size, minPrice, maxPrice);
    }
}
