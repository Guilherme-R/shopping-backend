package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.repository.ProductRepository;
import gsobrinho.shoppingbackend.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(final Long idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(idProduct.toString())));
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(final Product product) {
        findById(product.getProductId());
        return save(product);
    }

    @Override
    public void updateActive(final Long idProduct, final Boolean isActive) {
        Product product = findById(idProduct);
        product.setIsActive(isActive);
        save(product);
    }
}
