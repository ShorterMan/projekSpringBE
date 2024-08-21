package com.okta.productManagement.ProductManagement.service;

import com.okta.productManagement.ProductManagement.entity.ProductType;
import com.okta.productManagement.ProductManagement.exception.ResourceNotFoundException;
import com.okta.productManagement.ProductManagement.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public Page<ProductType> findAll(Pageable pageable) {
        return productTypeRepository.findAll(pageable);
    }

    public Optional<ProductType> findById(Long id) {
        return productTypeRepository.findById(id);
    }

    public ProductType save(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public void deleteById(Long id) {
        productTypeRepository.deleteById(id);
    }

    public ProductType updateProductType(Long id, ProductType updatedProductType) {
        return productTypeRepository.findById(id)
                .map(productType -> {
                    productType.setTypedesc(updatedProductType.getTypedesc());
                    return productTypeRepository.save(productType);
                })
                .orElseThrow(() -> new ResourceNotFoundException("ProductType not found with id " + id));
    }
}
