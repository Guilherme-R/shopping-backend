package gsobrinho.shoppingbackend.service;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.repository.ProductRepository;
import gsobrinho.shoppingbackend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository productRepository;


    @Test
    void findByIdProduct(){
        final Long idProduct = 1L;
        final Product product = Product.builder()
                .idProduct(idProduct)
                .name("TV")
                .value(BigDecimal.TEN)
                .build();

        when(productRepository.findById(idProduct))
            .thenReturn(Optional.of(product));

        Product returnedProduct = service.findById(idProduct);

        Assertions.assertNotNull(returnedProduct);
    }

    @Test
    void findAllWithlistNotEmpty(){
        //Scene
        final Iterable<Product> iterable = Arrays.asList(new Product(), new Product());

        when(productRepository.findAll()).thenReturn(iterable);

        //Action
        final List<Product> lsProduct = service.findAll();

        //Validation
        Assertions.assertFalse(lsProduct.isEmpty());
    }

    @Test
    void saveProductAndReturnWithNewId(){
        //Scene
        final Product product = Product.builder()
                .name("TV")
                .value(BigDecimal.TEN)
                .isActive(Boolean.TRUE)
                .build();

        when(productRepository.save(any()))
            .thenReturn(Product.builder().idProduct(1L).build());

        //Action
        Product returnedProduct = service.save(product);

        //Validation
        assertNotNull(returnedProduct.getIdProduct());
    }

    @Test
    void updateByIdProduct(){
        //Scene
        final Product product = Product.builder()
                .idProduct(1L)
                .name("TV")
                .value(new BigDecimal("100.01"))
                .isActive(Boolean.TRUE)
                .build();

        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        //Action
        Product returnedProduct = service.update(product);

        //Validation
        assertEquals(0, new BigDecimal("100.01").compareTo(returnedProduct.getValue()));
    }
}
