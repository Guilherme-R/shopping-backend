package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.repository.DepartmentRepository;
import gsobrinho.shoppingbackend.domain.repository.ProductDepartmentParityRepository;
import gsobrinho.shoppingbackend.domain.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ProductDepartmentParityRepository productDepartmentParityRepository;

    public Department findById(final Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(departmentId.toString())));
    }

    public List<Department> findByProductId(Long productId) {
        return departmentRepository.findByProductId(productId);
    }

    public List<Department> findAllByIds(List<Long> lsDepartmentId) {
        return StreamSupport.stream(departmentRepository.findAllById(lsDepartmentId).spliterator(), false).toList();
    }

    public List<Department> findAll() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false).toList();
    }

    @Transactional
    public Department save(final Department department){
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(final Department department) {
        findById(department.getId());
        return save(department);
    }

    @Transactional
    public void deactivateDepartment(final Long departmentId) {
        Department department = findById(departmentId);
        department.setIsActive(Boolean.FALSE);
        save(department);

        deleteAllParities(departmentId);
    }

    private void deleteAllParities(final Long departmentId){
        log.info("Deleting all parity records for department ID: {}.", departmentId);
        productDepartmentParityRepository.deleteByDepartmentId(departmentId);
    }
}
