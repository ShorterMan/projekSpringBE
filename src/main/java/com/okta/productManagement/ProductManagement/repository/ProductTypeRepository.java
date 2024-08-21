package com.okta.productManagement.ProductManagement.repository;

import com.okta.productManagement.ProductManagement.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
