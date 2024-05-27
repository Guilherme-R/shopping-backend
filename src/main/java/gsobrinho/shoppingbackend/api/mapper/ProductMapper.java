package gsobrinho.shoppingbackend.api.mapper;

import gsobrinho.shoppingbackend.api.dto.ProductDto;
import gsobrinho.shoppingbackend.domain.constants.enumerator.ProductStatusEnum;
import gsobrinho.shoppingbackend.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(source = "productStatus", target = "productStatusId", qualifiedByName = "enumToProductStatusId")
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(source = "productStatusId", target = "productStatus", qualifiedByName = "productStatusIdToEnum")
    ProductDto productToProductDto(Product product);

    default List<ProductDto> lsProductToLsProductDto(List<Product> lsProduct) {
        return lsProduct.stream().map(this::productToProductDto).toList();
    }

    @Named("enumToProductStatusId")
    default Long enumToProductStatusId(ProductStatusEnum productStatus) {
        return productStatus == null ? null : productStatus.getId();
    }

    @Named("productStatusIdToEnum")
    default ProductStatusEnum productStatusIdToEnum(Long productStatusId) {
        return productStatusId == null ? null : ProductStatusEnum.valueOf(productStatusId);
    }
}