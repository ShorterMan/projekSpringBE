package com.okta.productManagement.ProductManagement.repository;

import com.okta.productManagement.ProductManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
