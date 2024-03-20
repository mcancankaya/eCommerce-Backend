package com.mcancankaya.ecommerce.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String description;
    private CategoryResponse category;
    private ModelResponse model;
}
