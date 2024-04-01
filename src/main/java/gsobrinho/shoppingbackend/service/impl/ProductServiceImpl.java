package gsobrinho.shoppingbackend.service.impl;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.repository.ProductWriteRepository;
import gsobrinho.shoppingbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductWriteRepository productWriteRepository;

    @Override
    public Product save(final Product product) {
        return productWriteRepository.save(product);
    }
}
