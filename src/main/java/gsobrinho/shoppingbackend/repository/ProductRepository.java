package gsobrinho.shoppingbackend.repository;

import gsobrinho.shoppingbackend.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
