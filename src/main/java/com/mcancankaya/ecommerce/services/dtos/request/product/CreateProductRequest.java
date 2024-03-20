package com.mcancankaya.ecommerce.services.dtos.request.product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min= 6)
    private String name;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 6)
    private String description;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer modelId;
}
