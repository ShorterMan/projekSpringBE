package com.okta.productManagement.ProductManagement.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReqOrderProduct {
    private String name;
    private String productType;
    private Long price;
    private Long quantity;
}
