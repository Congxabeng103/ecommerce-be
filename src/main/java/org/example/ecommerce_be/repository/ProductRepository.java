package org.example.ecommerce_be.repository;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p "+
            "LEFT JOIN FETCH p.images " +
            "WHERE p.productInfo.id = :id " )
    List<Product> findProductById(
            @Param("id") Long id

    );

    @Query("SELECT p FROM Product p " +
            "WHERE p.color = :color " +
            "AND p.productInfo.id = :id " )

    List<Product> findByColor(
            @Param("id") Long id,
            @Param("color") String color

    );







}
