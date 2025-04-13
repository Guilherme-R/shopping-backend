package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long idProduct);

    List<Product> findAll();

    List<Product> findAllByDepartmentId(Long idDepartment);

    Product save(Product product);

    Product update(Product product);

    void updateActive(Long idProduct, Boolean isActive);

    void deleteAssociation(Long idProduct, List<Long> lsDepartment);
}
