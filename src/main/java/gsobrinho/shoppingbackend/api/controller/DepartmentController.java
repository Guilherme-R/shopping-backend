package gsobrinho.shoppingbackend.api.controller;

import gsobrinho.shoppingbackend.api.dto.DepartmentDto;
import gsobrinho.shoppingbackend.api.form.DepartmentForm;
import gsobrinho.shoppingbackend.api.mapper.DepartmentMapper;
import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final DepartmentMapper departmentMapper;


    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> findById(
            @PathVariable("departmentId") final Long departmentId){
        log.info("Searching department by id: {}", departmentId);
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(departmentService.findById(departmentId)));
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

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> update(
            @PathVariable final Long departmentId,
            @Valid @RequestBody final DepartmentForm departmentForm){
        log.info("Updating product with id: {}.", departmentId);
        departmentForm.setId(departmentId);
        Department department = departmentMapper.departmentFormToDepartment(departmentForm);
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(
                departmentService.update(department)));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(
            @PathVariable("departmentId") final Long departmentId){
        log.info("Updating activation to: {}, of department with id: {}.", Boolean.FALSE, departmentId);
        departmentService.deactivateDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }
}
