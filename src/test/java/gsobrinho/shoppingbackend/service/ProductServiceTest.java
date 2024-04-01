package gsobrinho.shoppingbackend.service;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.repository.ProductWriteRepository;
import gsobrinho.shoppingbackend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductWriteRepository productWriteRepository;

    @Test
    public void saveProductAndReturnNewId(){
        //Scene
        Product product = new Product();
        product.setName("TV");
        product.setValue(BigDecimal.TEN);
        product.setIsActive(Boolean.TRUE);

        Mockito.when(productWriteRepository.save(any()))
            .thenReturn(Product.builder().id(1L).build());

        //Action
        Product returnedProduct = service.save(product);

        //Validation
        assertNotNull(returnedProduct.getId());
    }
}
