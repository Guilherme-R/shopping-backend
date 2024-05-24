package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.repository.ProductRepository;
import gsobrinho.shoppingbackend.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(final Long idProduct) {
        log.info("Searching product by id: {}.", idProduct);
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(idProduct.toString())));
    }

    @Override
    public List<Product> findAll() {
        log.info("Searching a list of products.");
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Product save(final Product product) {
        log.info("Saving product with name: {}.", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product update(final Product product) {
        log.info("Updating product with id: {}.", product.getProductId());
        findById(product.getProductId());
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
