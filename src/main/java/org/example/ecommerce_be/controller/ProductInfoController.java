package org.example.ecommerce_be.controller;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.ProductInfo;
import org.example.ecommerce_be.service.ProductInfoService;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174")
public class ProductInfoController {


    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping
    public Page<ProductInfo> getAllProductsPageable(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productInfoService.getAllProductInfo(currentPage, pageSize);
    }

    @GetMapping("/sort-price-asc")
    public Page<ProductInfo> sortProductByPriceAsc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productInfoService.sortProductByPriceAsc(currentPage, pageSize);
    }
    @GetMapping("/sort-price-desc")
    public Page<ProductInfo> sortProductByPriceDesc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        return productInfoService.sortProductByPriceDesc(currentPage, pageSize);
    }
    @GetMapping("/filter")
    public Page<ProductInfo> getFilteredProducts(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return productInfoService.getFilteredProducts(currentPage,pageSize,categories, minPrice, maxPrice);
    }

    @GetMapping("/sort-price-asc/filter")
    public Page<ProductInfo> getFilteredProductsAsc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return productInfoService.getFilteredProductsAsc(currentPage,pageSize,categories, minPrice, maxPrice);
    }
    @GetMapping("/sort-price-desc/filter")
    public Page<ProductInfo> getFilteredProductsDesc(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return productInfoService.getFilteredProductsDesc(currentPage,pageSize,categories, minPrice, maxPrice);
    }
}
