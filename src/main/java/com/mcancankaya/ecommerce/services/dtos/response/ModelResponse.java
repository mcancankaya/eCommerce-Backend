package com.mcancankaya.ecommerce.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse {
    private Integer id;
    private String name;
    private BrandResponse brand;
}
