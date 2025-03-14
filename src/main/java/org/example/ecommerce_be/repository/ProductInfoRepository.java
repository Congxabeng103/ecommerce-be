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

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo,Long> {
    @Query("SELECT pi FROM ProductInfo pi "+
            "LEFT JOIN FETCH pi.images " +
            "WHERE (:categories IS NULL OR pi.category.id IN :categories) " +
            "AND (:minPrice IS NULL OR pi.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR pi.price <= :maxPrice)")
    Page<ProductInfo> findProductInfosByFilters(
            @Param("categories") List<Long> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );


    @Query("SELECT pi FROM ProductInfo pi "+
            "LEFT JOIN FETCH pi.images " +
            "WHERE (:categories IS NULL OR pi.category.id IN :categories) " +
            "AND (:minPrice IS NULL OR pi.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR pi.price <= :maxPrice)" +
            "ORDER BY pi.price ASC") // Sắp xếp theo giá tăng dần
    Page<ProductInfo> findProductInfosByFiltersAsc(
            @Param("categories") List<Long> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );

    @Query("SELECT pi FROM ProductInfo pi "+
            "LEFT JOIN FETCH pi.images " +
            "WHERE (:categories IS NULL OR pi.category.id IN :categories) " +
            "AND (:minPrice IS NULL OR pi.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR pi.price <= :maxPrice)" +
            "ORDER BY pi.price DESC") // Sắp xếp theo giá tăng dần

    Page<ProductInfo> findProductInfosByFiltersDesc(
            @Param("categories") List<Long> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );
    Page<ProductInfo> findAllByOrderByPriceAsc(Pageable pageable);
    Page<ProductInfo> findAllByOrderByPriceDesc(Pageable pageable);

}
