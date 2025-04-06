package gsobrinho.shoppingbackend.api.controller;

import gsobrinho.shoppingbackend.api.dto.ProductDto;
import gsobrinho.shoppingbackend.api.form.ProductForm;
import gsobrinho.shoppingbackend.api.mapper.ProductMapper;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable final Long idProduct){
        log.info("Searching product by id: {}.", idProduct);
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.findById(idProduct)));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        log.info("Searching a list of products.");
        return ResponseEntity.ok(productMapper.lsProductToLsProductDto(
                productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(
            @Valid @RequestBody final ProductForm productForm){
        Product product = productMapper.productFormToProduct(productForm);
        product.setProductId(null);
        log.info("Saving product with name: {}.", product.getName());
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.save(product)));
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductDto> update(
            @PathVariable final Long idProduct,
            @Valid @RequestBody final ProductForm productForm){
        log.info("Updating product with id: {}.", idProduct);
        productForm.setProductId(idProduct);
        Product product = productMapper.productFormToProduct(productForm);
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.update(product)));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> updateIsActive(
            @PathVariable final Long idProduct){
        log.info("Updating activation to: {}, of product with id: {}.", Boolean.FALSE, idProduct);
        productService.updateActive(idProduct, Boolean.FALSE);
        return ResponseEntity.noContent().build();
    }
}
