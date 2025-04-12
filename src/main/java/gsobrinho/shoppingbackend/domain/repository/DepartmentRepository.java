package gsobrinho.shoppingbackend.domain.repository;

import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
