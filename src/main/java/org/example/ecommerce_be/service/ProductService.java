package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getAllProductsPageable(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productRepository.findAll(pageable);
    }
    public Page<Product> getFilteredProducts(int currentPage,int pageSize,List<Long> category, Long color, Long size, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);

        return productRepository.findProductsByFilters(category, color, size, minPrice, maxPrice,pageable );
    }

    public Page<Product> sortProductByPriceAsc(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productRepository.findAllByOrderByPriceAsc(pageable);
    }
    public Page<Product> sortProductByPriceDesc(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productRepository.findAllByOrderByPriceDesc(pageable);
    }
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

}
