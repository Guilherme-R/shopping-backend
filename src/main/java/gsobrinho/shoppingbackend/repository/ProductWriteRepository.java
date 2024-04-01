package gsobrinho.shoppingbackend.repository;

import gsobrinho.shoppingbackend.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductWriteRepository extends CrudRepository<Product, Long> {
}
