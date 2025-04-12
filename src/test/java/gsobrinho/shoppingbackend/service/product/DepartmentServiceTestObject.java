package gsobrinho.shoppingbackend.service.product;

import com.fasterxml.jackson.core.type.TypeReference;
import gsobrinho.shoppingbackend.core.utils.JsonUtils;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DepartmentServiceTestObject {

    private final Department department;

    public DepartmentServiceTestObject(){
        department = JsonUtils.fileToObject("src/test/resource/json/product/departmentDto.json",
                new TypeReference<Department>() {});
    }
}
