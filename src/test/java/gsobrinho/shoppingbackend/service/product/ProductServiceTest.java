package gsobrinho.shoppingbackend.service.product;

import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.repository.ProductRepository;
import gsobrinho.shoppingbackend.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @InjectMocks
    private ProductServiceTestObject objTest;

    @Mock
    private ProductRepository productRepository;


    @Test
    void findByIdProduct(){
        final Long idProduct = 1L;

        when(productRepository.findById(idProduct))
            .thenReturn(Optional.of(objTest.getProduct()));

        Product returnedProduct = service.findById(idProduct);

        Assertions.assertNotNull(returnedProduct);
        Assertions.assertEquals(returnedProduct.getId(), idProduct);
    }

    @Test
    void findAllWithListNotEmpty(){
        //Scene
        final Iterable<Product> iterable = Arrays.asList(
                objTest.getProduct(), objTest.getProduct());

        when(productRepository.findAll()).thenReturn(iterable);

        //Action
        final List<Product> lsProduct = service.findAll();

        //Validation
        Assertions.assertFalse(lsProduct.isEmpty());
    }

    @Test
    void saveProductAndReturnNewId(){
        //Scene
        when(productRepository.save(any()))
            .thenReturn(Product.builder().id(1L).build());

        //Action
        Product returnedProduct = service.save(objTest.getProduct());

        //Validation
        assertNotNull(returnedProduct.getId());
    }

    @Test
    void updateByIdProduct(){
        //Scene
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(objTest.getProduct()));
        when(productRepository.save(any()))
                .thenReturn(objTest.getProduct());

        //Action
        Product returnedProduct = service.update(objTest.getProduct());

        //Validation
        assertEquals(0, new BigDecimal("2500.00").compareTo(returnedProduct.getPrice()));
    }

    @Test
    void updateProductIsActive(){
        final Long idProduct = 1L;

        //Scene
        when(productRepository.findById(anyLong())).thenReturn(
                Optional.of(objTest.getProduct()));

        //Action
        service.updateActive(idProduct, true);

        //Validation
        verify(productRepository, times(1)).save(any());
    }
}
