package gsobrinho.shoppingbackend.controller;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shopping/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findById(@PathVariable final Long idProduct){
        return ResponseEntity.ok(productService.findById(idProduct));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody final Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<Product> update(
            @PathVariable final Long idProduct,
            @RequestBody final Product product){
        product.setIdProduct(idProduct);
        return ResponseEntity.ok(productService.update(product));
    }
}
