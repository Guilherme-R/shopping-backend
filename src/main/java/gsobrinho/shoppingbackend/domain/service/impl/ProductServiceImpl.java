package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import gsobrinho.shoppingbackend.domain.model.Product;
import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;
import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;
import gsobrinho.shoppingbackend.domain.repository.ProductRepository;
import gsobrinho.shoppingbackend.domain.service.DepartmentService;
import gsobrinho.shoppingbackend.domain.service.ProductDepartmentParityService;
import gsobrinho.shoppingbackend.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final DepartmentService departmentService;

    private final ProductDepartmentParityService productDepartmentParityService;

    private final ProductRepository productRepository;


    public Product findById(final Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id: ".concat(idProduct.toString())));

        product.setLsDepartment(departmentService.findByProductId(product.getId()));
        return product;
    }

    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).toList();
    }

    public List<Product> findAllByDepartmentId(final Long departmentId) {
        return productRepository.findAllByDepartmentId(departmentId);
    }

    @Transactional
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(final Product product) {
        findById(product.getId());
        Product savedProduct = save(product);

        if(!CollectionUtils.isEmpty(product.getLsDepartment()))
            associateProductToDepartments(product);

        savedProduct.setLsDepartment(departmentService.findByProductId(product.getId()));
        return savedProduct;
    }

    private void associateProductToDepartments(final Product product) {
        log.debug("Associating product with ID: {} to department IDS: {}", product.getId(), product.getLsDepartment());
        List<ProductDepartmentParity> lsParity = product.getLsDepartment().stream()
                .map(department ->
                    ProductDepartmentParity.builder()
                        .id(new ProductDepartmentParityId())
                        .product(product)
                        .department(department)
                        .build()
                ).toList();

        productDepartmentParityService.saveNonExistingIds(product.getId(), lsParity);
    }

    @Transactional
    public void updateActive(final Long idProduct, final Boolean isActive) {
        Product product = findById(idProduct);
        product.setIsActive(isActive);
        save(product);
    }

    @Transactional
    public void deleteAssociation(final Long idProduct, final List<Long> ids) {
        productDepartmentParityService.deleteAssociation(idProduct, ids);
    }
}
