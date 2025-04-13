package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long idDepartment);

    List<Department> findByProductId(Long productId);

    List<Department> findAllByIds(List<Long> lsIdDepartment);

    List<Department> findAll();

    Department save(final Department department);

    Department update(final Department department);

    void deactivateDepartment(Long idDepartment);
}
