package com.mcancankaya.ecommerce.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer stockAmount;
    private CategoryResponse category;
    private ModelResponse model;
    private BigDecimal price;
    private Boolean isActive;
    private String imageUrl;
}
