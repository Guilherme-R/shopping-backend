package gsobrinho.shoppingbackend.domain.model;

import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_department_parity")
public class ProductDepartmentParity {

    @EmbeddedId
    private ProductDepartmentParityId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
