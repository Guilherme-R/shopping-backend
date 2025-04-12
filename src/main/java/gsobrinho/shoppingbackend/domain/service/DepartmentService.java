package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long idDepartment);

    List<Department> findAll();

    Department save(final Department department);

    Department update(final Department department);

    void updateActive(Long idDepartment, final Boolean isActive);
}
