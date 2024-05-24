package gsobrinho.shoppingbackend.service.product;

import com.fasterxml.jackson.core.type.TypeReference;
import gsobrinho.shoppingbackend.core.utils.JsonUtils;
import gsobrinho.shoppingbackend.domain.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.File;

@Getter
@Component
public class ProductServiceTestObject {

    private final Product product;

    public ProductServiceTestObject(){
        product = JsonUtils.fileToObject("src/test/resource/json/product/productDto.json",
                new TypeReference<Product>() {});
    }
}
