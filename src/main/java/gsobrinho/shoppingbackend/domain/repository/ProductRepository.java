package gsobrinho.shoppingbackend.domain.repository;

import gsobrinho.shoppingbackend.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = """
            SELECT p.*
            FROM product_department_parity pdp 
            JOIN product p
                ON p.id = pdp.product_id
            WHERE pdp.department_id = :departmentId
    """, nativeQuery = true)
    List<Product> findAllByDepartmentId(Long departmentId);
}
