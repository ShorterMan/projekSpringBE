package com.okta.productManagement.ProductManagement.service;

import com.okta.productManagement.ProductManagement.dto.ReqOrderProduct;
import com.okta.productManagement.ProductManagement.entity.Product;
import com.okta.productManagement.ProductManagement.entity.ProductType;
import com.okta.productManagement.ProductManagement.exception.ResourceNotFoundException;
import com.okta.productManagement.ProductManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Autowired
    private ProductTypeService productTypeService;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setQuantity(updatedProduct.getQuantity());
                    product.setProductType(updatedProduct.getProductType());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public List<Product> saveAll(List<ReqOrderProduct> products) {
        List<Product> productDatas = new ArrayList<>();
        for (ReqOrderProduct reqOrderProduct : products) {
            Product product = new Product();
            Optional<ProductType> productType = productTypeService.findById(Long.valueOf(reqOrderProduct.getProductType()));
            product.setProductType(productType.orElse(null));
            product.setName(reqOrderProduct.getName());
            product.setPrice(reqOrderProduct.getPrice());
            product.setQuantity(reqOrderProduct.getQuantity());
            productDatas.add(product);
        }
        return productRepository.saveAll(productDatas);
    }
}
