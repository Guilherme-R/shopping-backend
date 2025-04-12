package gsobrinho.shoppingbackend.api.controller;

import gsobrinho.shoppingbackend.api.dto.DepartmentDto;
import gsobrinho.shoppingbackend.api.dto.ProductDto;
import gsobrinho.shoppingbackend.api.form.DepartmentForm;
import gsobrinho.shoppingbackend.api.form.ProductForm;
import gsobrinho.shoppingbackend.api.mapper.DepartmentMapper;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;


    @GetMapping("/{idDepartment}")
    public ResponseEntity<DepartmentDto> findById(
            @PathVariable("idDepartment") final Long idDepartment){
        log.info("Searching department by id: {}", idDepartment);
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(departmentService.findById(idDepartment)));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll(){
        log.info("Searching a list of departments.");
        return ResponseEntity.ok(departmentMapper.lsDepartmentToLsDepartmentDto(departmentService.findAll()));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> save(
            @Valid @RequestBody final DepartmentForm departmentForm){
        Department department = departmentMapper.departmentFormToDepartment(departmentForm);
        department.setId(null);
        log.info("Saving product with name: {}.", department.getName());
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(
                departmentService.save(department)));
    }

    @PutMapping("/{idDepartment}")
    public ResponseEntity<DepartmentDto> update(
            @PathVariable final Long idDepartment,
            @Valid @RequestBody final DepartmentForm departmentForm){
        log.info("Updating product with id: {}.", idDepartment);
        departmentForm.setId(idDepartment);
        Department department = departmentMapper.departmentFormToDepartment(departmentForm);
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(
                departmentService.update(department)));
    }

    @DeleteMapping("/{idDepartment}")
    public ResponseEntity<?> deleteDepartment(
            @PathVariable("idDepartment") final Long idDepartment){
        log.info("Updating activation to: {}, of department with id: {}.", Boolean.FALSE, idDepartment);
        departmentService.updateActive(idDepartment, Boolean.FALSE);
        return ResponseEntity.noContent().build();
    }
}
