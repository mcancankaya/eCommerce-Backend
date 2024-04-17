package com.mcancankaya.ecommerce.services.dtos.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String description;
    @NotNull
    private Integer stockAmount;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer modelId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Boolean isActive;
    private String imageUrl;
}
