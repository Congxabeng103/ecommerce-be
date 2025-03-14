package org.example.ecommerce_be.dto;

import org.example.ecommerce_be.entity.ProductInfoImage;

import java.math.BigDecimal;
import java.util.List;

public class ProductInfoDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<ProductInfoImage> images;
    private String description;
    private Long categoryId;
    private String categoryName;

    public ProductInfoDTO(Long id, String name, BigDecimal price, List<ProductInfoImage> imageUrls, String description, Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.description = description;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getters & Setters
}
