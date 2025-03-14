package org.example.ecommerce_be.dto;

import org.example.ecommerce_be.entity.Category;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.ProductInfo;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {
    public static CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static ProductInfoDTO toProductInfoDTO(ProductInfo productInfo) {
        return new ProductInfoDTO(
                productInfo.getId(),
                productInfo.getName(),
                productInfo.getPrice(),
                productInfo.getImages(),
                productInfo.getDescription(),
                productInfo.getCategory().getId(),
                productInfo.getCategory().getName()
        );
    }

    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductInfo().getId(),
                product.getProductInfo().getName(),
                product.getPrice(),
                product.getImages(),
                product.getColor(),
                product.getSize()
        );
    }

    public static List<ProductInfoDTO> toProductInfoDTOList(List<ProductInfo> products) {
        return products.stream().map(DTOMapper::toProductInfoDTO).collect(Collectors.toList());
    }

    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream().map(DTOMapper::toProductDTO).collect(Collectors.toList());
    }
}
