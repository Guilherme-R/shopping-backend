package gsobrinho.shoppingbackend.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "quantity", nullable = false)
    private String quantity;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "discount_pct", nullable = false)
    private BigDecimal discountPct;
    @Column(name = "product_status_id", nullable = false)
    private Integer productStatusId;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
