package org.example.ecommerce_be.service;

import org.example.ecommerce_be.dto.SizeQuantityDTO;
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

//    public Page<Product> getAllProductsPageable(int currentPage, int pageSize) {
//        Pageable pageable = PageRequest.of(currentPage, pageSize);
//        Page<Product> products = productRepository.findAll(pageable);
//        return products;
//    }


    public List<Product> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> filterByColor(Long id, String color) {
        return productRepository.filterByColor(id, color);
    }
    public Product filterByColorAndSize(Long id, String color, String size) {
        return productRepository.filterByColorAndSize(id, color, size);
    }

    public List<String> getDistinctColors(Long productId) {
        return productRepository.findDistinctColorsByProductInfoId(productId);

    }


     public List<SizeQuantityDTO> getDistinctSizes(Long productId) {
         return productRepository.findDistinctSizesByProductInfoId(productId);
     }
}
