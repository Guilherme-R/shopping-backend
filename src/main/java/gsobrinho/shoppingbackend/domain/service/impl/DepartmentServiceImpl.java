package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.model.Department;
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
    public Department findById(final Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(departmentId.toString())));
    }

    @Override
    public List<Department> findByProductId(Long productId) {
        return departmentRepository.findByProductId(productId);
    }

    @Override
    public List<Department> findAllByIds(List<Long> lsDepartmentId) {
        return StreamSupport.stream(departmentRepository.findAllById(lsDepartmentId).spliterator(), false).toList();
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
    public void updateActive(final Long departmentId, final Boolean isActive) {
        Department department = findById(departmentId);
        department.setIsActive(isActive);
        save(department);
    }
}
