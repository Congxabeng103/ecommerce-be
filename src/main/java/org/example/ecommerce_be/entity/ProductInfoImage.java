package org.example.ecommerce_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_info_images")
public class ProductInfoImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_info_id", nullable = false)
    @JsonBackReference  // ✅ Giúp Jackson tránh vòng lặp vô hạn
    private ProductInfo productInfo;

    @Column(nullable = false)
    private String imageUrl;
}
