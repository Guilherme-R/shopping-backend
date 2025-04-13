package gsobrinho.shoppingbackend.domain.service.impl;

import gsobrinho.shoppingbackend.domain.model.ProductDepartmentParity;
import gsobrinho.shoppingbackend.domain.model.id.ProductDepartmentParityId;
import gsobrinho.shoppingbackend.domain.repository.ProductDepartmentParityRepository;
import gsobrinho.shoppingbackend.domain.service.ProductDepartmentParityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductDepartmentParityServiceImpl implements ProductDepartmentParityService {

    private final ProductDepartmentParityRepository productDepartmentParityRepository;

    @Transactional
    public List<ProductDepartmentParity> save(List<ProductDepartmentParity> lsParity) {
        return StreamSupport.stream(productDepartmentParityRepository.saveAll(lsParity).spliterator(), false)
                .toList();
    }

    @Transactional
    public List<ProductDepartmentParity> saveNonExistingIds(
            final Long productId,
            final List<ProductDepartmentParity> lsParity
    ) {
        List<Long> lsIdNotAssociated = findLsIdNotAssociated(productId, lsParity);

        if(CollectionUtils.isEmpty(lsIdNotAssociated))
            return new ArrayList<>();
        return save(findDepartmentsToInsert(lsParity, lsIdNotAssociated));
    }

    private List<Long> findLsIdNotAssociated(
            final Long productId,
            final List<ProductDepartmentParity> lsParity
    ){
        return productDepartmentParityRepository
                .findNonExistingIds(productId, lsParity.stream().map(item -> item.getDepartment().getId()).toList());
    }

    private List<ProductDepartmentParity> findDepartmentsToInsert(
            final List<ProductDepartmentParity> lsParity,
            final List<Long> nonExistingIds
    ){
        return lsParity.stream().filter(item -> nonExistingIds.stream()
                    .anyMatch(item1 -> item1.equals(item.getDepartment().getId()))
                ).toList();
    }

    @Transactional
    public void deleteAssociation(final Long productId, final List<Long> ids) {
        productDepartmentParityRepository.deleteAllById(
                ids.stream().map(id -> new ProductDepartmentParityId(productId, id)).toList()
        );
    }

    @Transactional
    public void deleteByDepartmentId(final Long departmentId) {
        productDepartmentParityRepository.deleteByDepartmentId(departmentId);
    }
}
