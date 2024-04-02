package gsobrinho.shoppingbackend.service.impl;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.repository.ProductRepository;
import gsobrinho.shoppingbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(final Long idProduct) {
        return productRepository.findById(idProduct)
            .orElseThrow(() -> new RuntimeException("Entity not found!"));
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }
}
