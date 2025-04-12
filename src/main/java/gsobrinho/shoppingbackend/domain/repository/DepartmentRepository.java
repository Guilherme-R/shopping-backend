package gsobrinho.shoppingbackend.domain.repository;

import gsobrinho.shoppingbackend.domain.model.Department;
import gsobrinho.shoppingbackend.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = """
        SELECT d.*
        FROM product_department_parity pdp
        JOIN department d
            ON d.id = pdp.department_id
        WHERE pdp.product_id = :productId
    """, nativeQuery = true)
    List<Department> findByProductId(@Param("productId") Long productId);
}
