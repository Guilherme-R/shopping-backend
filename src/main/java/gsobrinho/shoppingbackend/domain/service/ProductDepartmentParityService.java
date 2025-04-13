package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;

import java.util.List;

public interface ProductDepartmentParityService {

    List<ProductDepartmentParity> save(List<ProductDepartmentParity> lsParity);

    List<ProductDepartmentParity> saveNonExistingIds(Long productId, List<ProductDepartmentParity> lsParity);

    void deleteAssociation(Long productId, List<Long> lsDepartment);

    void deleteByDepartmentId(Long departmentId);
}
