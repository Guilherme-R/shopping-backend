package gsobrinho.shoppingbackend.service;

import gsobrinho.shoppingbackend.domain.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long idProduct);

    List<Product> findAll();

    Product save(Product product);

    Product update(Product product);
}
