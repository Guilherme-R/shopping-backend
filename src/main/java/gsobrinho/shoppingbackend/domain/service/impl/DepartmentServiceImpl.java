package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.api.dto.DepartmentDto;
import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.repository.DepartmentRepository;
import gsobrinho.shoppingbackend.domain.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department findById(final Long idDepartment) {
        return departmentRepository.findById(idDepartment)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(idDepartment.toString())));
    }

    @Override
    public List<Department> findAll() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Department save(final Department department){
        return departmentRepository.save(department);
    }

    @Override
    public Department update(final Department department) {
        findById(department.getId());
        return save(department);
    }

    @Override
    public void updateActive(final Long idDepartment, final Boolean isActive) {
        Department department = findById(idDepartment);
        department.setIsActive(isActive);
        save(department);
    }
}
