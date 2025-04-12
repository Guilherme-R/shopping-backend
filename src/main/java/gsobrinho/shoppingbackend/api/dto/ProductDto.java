package gsobrinho.shoppingbackend.api.dto;

import gsobrinho.shoppingbackend.domain.constants.enumerator.ProductStatusEnum;
import gsobrinho.shoppingbackend.domain.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPct;
    private Boolean isActive;
    private ProductStatusEnum productStatus;
    private List<Department> lsDepartment;
}
