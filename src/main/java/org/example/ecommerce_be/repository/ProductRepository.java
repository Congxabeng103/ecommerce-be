package org.example.ecommerce_be.repository;

import org.example.ecommerce_be.dto.SizeQuantityDTO;
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
            "WHERE p.productInfo.id = :id " +
            "AND p.color = :color")
    List<Product> filterByColor(
            @Param("id") Long id,
            @Param("color") String color


    );
    @Query("SELECT p FROM Product p " +
            "WHERE p.productInfo.id = :id " +
            "AND p.color = :color " +
            "AND p.size = :size")

    Product filterByColorAndSize(
            @Param("id") Long id,
            @Param("color") String color,
            @Param("size") String size


    );

    @Query("SELECT DISTINCT p.color FROM Product p WHERE p.productInfo.id = :id")
    List<String> findDistinctColorsByProductInfoId(@Param("id") Long id);

//    @Query("SELECT p.size AS size, SUM(p.quantity) AS totalQuantity FROM Product p WHERE p.productInfo.id = :id GROUP BY p.size")
//    List<Object[]> findDistinctSizesByProductInfoId(@Param("id") Long id);

    @Query("SELECT DISTINCT new org.example.ecommerce_be.dto.SizeQuantityDTO(p.size) " +
            "FROM Product p WHERE p.productInfo.id = :id " )
    List<SizeQuantityDTO> findDistinctSizesByProductInfoId(@Param("id") Long id);




}
