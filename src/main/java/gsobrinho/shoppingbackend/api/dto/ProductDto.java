package gsobrinho.shoppingbackend.api.dto;

import gsobrinho.shoppingbackend.domain.constants.enumerator.ProductStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private String quantity;
    private BigDecimal price;
    private BigDecimal discountPct;
    private Boolean isActive;
    private ProductStatusEnum productStatus;
}
