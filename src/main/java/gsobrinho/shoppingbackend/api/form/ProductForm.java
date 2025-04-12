package gsobrinho.shoppingbackend.api.form;

import gsobrinho.shoppingbackend.domain.constants.enumerator.ProductStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ProductForm {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    @PositiveOrZero
    private Integer quantity;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    private BigDecimal discountPct;
    @NotNull
    private Boolean isActive;
    @NotNull
    private ProductStatusEnum productStatus;
}
