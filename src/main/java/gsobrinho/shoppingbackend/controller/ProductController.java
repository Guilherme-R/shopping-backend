package gsobrinho.shoppingbackend.controller;

import gsobrinho.shoppingbackend.domain.Product;
import gsobrinho.shoppingbackend.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/shopping/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody final Product product){
        return ResponseEntity.ok(productService.save(product));
    }
}
