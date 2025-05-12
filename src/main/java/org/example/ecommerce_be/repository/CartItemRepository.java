package org.example.ecommerce_be.repository;

import org.example.ecommerce_be.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c FROM CartItem c WHERE c.cart.userEmail = :userEmail")
    List<CartItem> findByUserEmail(@Param("userEmail") String userEmail);

}