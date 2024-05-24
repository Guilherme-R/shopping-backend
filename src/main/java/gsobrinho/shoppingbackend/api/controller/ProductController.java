package gsobrinho.shoppingbackend.api.controller;

import gsobrinho.shoppingbackend.api.dto.ProductDto;
import gsobrinho.shoppingbackend.api.mapper.ProductMapper;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shopping/product")
public class ProductController {
    
    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable final Long idProduct){
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.findById(idProduct)));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productMapper.lsProductToLsProductDto(
                productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(
            @Valid @RequestBody final ProductDto productDto){
        Product product = productMapper.productDtoToProduct(productDto);
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.save(product)));
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductDto> update(
            @PathVariable final Long idProduct,
            @Valid @RequestBody final ProductDto productDto){
        productDto.setProductId(idProduct);
        Product product = productMapper.productDtoToProduct(productDto);
        return ResponseEntity.ok(productMapper.productToProductDto(
                productService.update(product)));
    }

    @PutMapping("/isActive/{idProduct}")
    public ResponseEntity<Void> updateIsActive(
            @PathVariable final Long idProduct,
            @RequestParam(required = true) final Boolean isActive){
        productService.updateActive(idProduct, isActive);
        return ResponseEntity.noContent().build();
    }
}
