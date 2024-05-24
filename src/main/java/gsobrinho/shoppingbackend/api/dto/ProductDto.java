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
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    private BigDecimal discountPct;
    @NotNull
    private Boolean isActive;
    @NotNull
    private ProductStatusEnum productStatus;
}
