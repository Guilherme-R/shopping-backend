package gsobrinho.shoppingbackend.api.mapper;

import gsobrinho.shoppingbackend.api.dto.DepartmentDto;
import gsobrinho.shoppingbackend.api.dto.ProductDto;
import gsobrinho.shoppingbackend.api.form.DepartmentForm;
import gsobrinho.shoppingbackend.api.form.ProductForm;
import gsobrinho.shoppingbackend.domain.constants.enumerator.ProductStatusEnum;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    Department departmentFormToDepartment(DepartmentForm departmentForm);

    DepartmentDto departmentToDepartmentDto(Department department);

    default List<DepartmentDto> lsDepartmentToLsDepartmentDto(List<Department> lsProduct) {
        return lsProduct.stream().map(this::departmentToDepartmentDto).toList();
    }
}