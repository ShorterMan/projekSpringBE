package com.okta.productManagement.ProductManagement.controller;

import com.okta.productManagement.ProductManagement.entity.ProductType;
import com.okta.productManagement.ProductManagement.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/producttypes")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public Page<ProductType> getAllProductTypes(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productTypeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        return productTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductType createProductType(@RequestBody ProductType productType) {
        return productTypeService.save(productType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable Long id, @RequestBody ProductType productType) {
        return ResponseEntity.ok(productTypeService.updateProductType(id, productType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Long id) {
        productTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
