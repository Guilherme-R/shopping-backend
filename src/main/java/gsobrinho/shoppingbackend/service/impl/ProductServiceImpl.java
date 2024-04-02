package gsobrinho.shoppingbackend.service.impl;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.repository.ProductRepository;
import gsobrinho.shoppingbackend.service.ProductService;
import gsobrinho.shoppingbackend.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(final Long idProduct) {
        log.info("Searching product by id: {}.", idProduct);
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(idProduct.toString())));
    }

    @Override
    public List<Product> findAll() {
        log.info("Searching a list of products.");
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Product save(final Product product) {
        log.info("Saving product with name: {}.", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product update(final Product product) {
        log.info("Updating product with id: {}.", product.getIdProduct());
        findById(product.getIdProduct());
        return save(product);
    }

    @Override
    public void updateActive(final Long idProduct, final Boolean isActive) {
        log.info("Updating activation to: {}, of product with id: {}.", isActive, idProduct);
        Product product = findById(idProduct);
        product.setIsActive(isActive);

        save(product);
    }
}
