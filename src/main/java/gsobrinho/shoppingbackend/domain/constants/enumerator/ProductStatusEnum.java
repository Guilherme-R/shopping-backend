package gsobrinho.shoppingbackend.domain.constants.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProductStatusEnum {

    FOR_SALE(1L),
    ON_SALE(2L),
    SOLD_OUT(3L);

    private final Long id;

    public static ProductStatusEnum valueOf(long id) {
        for (ProductStatusEnum status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatusEnum id: " + id);
    }
}
