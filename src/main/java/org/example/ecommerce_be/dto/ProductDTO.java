package org.example.ecommerce_be.dto;

import org.example.ecommerce_be.entity.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    private Long id;
    private Long infoId;
    private String productName;
    private BigDecimal price;
    private List<ProductImage> images;
    private String color;
    private String size;

    public ProductDTO(Long id, Long infoId, String productName, BigDecimal price, List<ProductImage> images,String color, String size) {
        this.id = id;
        this.infoId = infoId;
        this.productName = productName;
        this.price = price;
        this.images = images;
        this.color = color;
        this.size = size;
    }

    // Getters & Setters
}
