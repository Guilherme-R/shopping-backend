package gsobrinho.shoppingbackend.domain.repository;

import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;
import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDepartmentParityRepository extends CrudRepository<ProductDepartmentParity, ProductDepartmentParityId> {

    @Query(value = """
        SELECT DISTINCT d.id
        FROM department d
        WHERE d.id IN (:ids)
          AND d.id NOT IN (
              SELECT DISTINCT department_id
              FROM product_department_parity
              WHERE product_id = :productId
          )
    """, nativeQuery = true)
    List<Long> findNonExistingIds(@Param("productId") Long productId, @Param("ids") List<Long> ids);

    void deleteByDepartmentId(Long departmentId);
}
