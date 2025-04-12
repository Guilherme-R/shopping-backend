package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;
import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;
import gsobrinho.shoppingbackend.domain.repository.ProductDepartmentParityRepository;
import gsobrinho.shoppingbackend.domain.service.ProductDepartmentParityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductDepartmentParityServiceImpl implements ProductDepartmentParityService {

    private final ProductDepartmentParityRepository productDepartmentParityRepository;

    @Override
    public List<ProductDepartmentParity> save(List<ProductDepartmentParity> lsParity) {
        return StreamSupport.stream(productDepartmentParityRepository.saveAll(lsParity).spliterator(), false)
                .toList();
    }

    @Override
    public List<ProductDepartmentParity> saveNonExistingIds(final Long productId, final List<ProductDepartmentParity> lsParity) {
        List<Long> nonExistingIds = productDepartmentParityRepository
                .findNonExistingIds(productId, lsParity.stream().map(item -> item.getDepartment().getId()).toList());

        if(CollectionUtils.isEmpty(nonExistingIds))
            return new ArrayList<>();
        return save(lsParity.stream().filter(item -> nonExistingIds.stream()
                .anyMatch(item1 -> item1.equals(item.getDepartment().getId())))
            .toList());
    }

    @Override
    public void deleteParity(final Long productId, final List<Long> lsDepartment) {
        productDepartmentParityRepository.deleteAllById(
                lsDepartment.stream().map(departmentId -> new ProductDepartmentParityId(productId, departmentId)).toList()
        );
    }
}
