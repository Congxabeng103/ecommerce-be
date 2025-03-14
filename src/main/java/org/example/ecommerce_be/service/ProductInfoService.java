package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.ProductInfo;
import org.example.ecommerce_be.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoService
{
    @Autowired
     private ProductInfoRepository productInfoRepository;

    public Page<ProductInfo> getFilteredProducts(int currentPage, int pageSize, List<Long> categories, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);

        return productInfoRepository.findProductInfosByFilters(categories, minPrice, maxPrice,pageable );
    }
    public Page<ProductInfo> getFilteredProductsAsc(int currentPage, int pageSize, List<Long> categories, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);

        return productInfoRepository.findProductInfosByFiltersAsc(categories, minPrice, maxPrice,pageable );
    }
    public Page<ProductInfo> getFilteredProductsDesc(int currentPage, int pageSize, List<Long> categories, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);

        return productInfoRepository.findProductInfosByFiltersDesc(categories, minPrice, maxPrice,pageable );
    }

    public Page<ProductInfo> sortProductByPriceAsc(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productInfoRepository.findAllByOrderByPriceAsc(pageable);
    }
    public Page<ProductInfo> sortProductByPriceDesc(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productInfoRepository.findAllByOrderByPriceDesc(pageable);
    }

    public Page<ProductInfo> getAllProductInfo(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return productInfoRepository.findAll(pageable);
    }
}
