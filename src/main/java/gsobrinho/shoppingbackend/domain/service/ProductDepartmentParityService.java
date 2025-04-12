package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;
import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;

import java.util.List;

public interface ProductDepartmentParityService {

    List<ProductDepartmentParity> save(List<ProductDepartmentParity> lsParity);

    List<ProductDepartmentParity> saveNonExistingIds(Long productId, List<ProductDepartmentParity> lsParity);

    void deleteParity(Long productId, List<Long> lsDepartment);
}
