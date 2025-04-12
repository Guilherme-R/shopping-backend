package gsobrinho.shoppingbackend.domain.service;

import gsobrinho.shoppingbackend.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long idProduct);

    List<Product> findAll();

    Product save(Product product);

    Product update(Product product);

    void updateActive(final Long idProduct, final Boolean isActive);

    void deleteDepartmentParity(final Long idProduct, List<Long> lsDepartment);
}
