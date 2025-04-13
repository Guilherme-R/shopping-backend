package gsobrinho.shoppingbackend.service.product;

import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.repository.DepartmentRepository;
import gsobrinho.shoppingbackend.domain.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl service;

    @InjectMocks
    private DepartmentServiceTestObject objTest;

    @Mock
    private DepartmentRepository departmentRepository;


    @Test
    void findByidDepartment(){
        final Long idDepartment = 1L;

        when(departmentRepository.findById(idDepartment))
            .thenReturn(Optional.of(objTest.getDepartment()));

        Department returnedDepartment = service.findById(idDepartment);

        Assertions.assertNotNull(returnedDepartment);
        Assertions.assertEquals(returnedDepartment.getId(), idDepartment);
    }

    @Test
    void findAllWithListNotEmpty(){
        //Scene
        final Iterable<Department> iterable = Arrays.asList(
                objTest.getDepartment(), objTest.getDepartment());

        when(departmentRepository.findAll()).thenReturn(iterable);

        //Action
        final List<Department> lsDepartment = service.findAll();

        //Validation
        Assertions.assertFalse(lsDepartment.isEmpty());
    }

    @Test
    void saveProductAndReturnNewId(){
        //Scene
        when(departmentRepository.save(any()))
            .thenReturn(Department.builder().id(2L).build());

        //Action
        Department returnedDepartment = service.save(objTest.getDepartment());

        //Validation
        assertNotNull(returnedDepartment.getId());
    }

    @Test
    void updateByidDepartment(){
        //Scene
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(objTest.getDepartment()));
        when(departmentRepository.save(any()))
                .thenReturn(objTest.getDepartment());

        //Action
        Department returnedDepartment = service.update(objTest.getDepartment());

        //Validation
        assertEquals(0, "Produtos de teste.".compareTo(returnedDepartment.getDescription()));
    }

    @Test
    void updateProductIsActive(){
        final Long idDepartment = 1L;

        //Scene
        when(departmentRepository.findById(anyLong())).thenReturn(
                Optional.of(objTest.getDepartment()));

        //Action
        service.deactivateDepartment(idDepartment);

        //Validation
        verify(departmentRepository, times(1)).save(any());
    }
}
