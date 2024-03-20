package com.mcancankaya.ecommerce.services.dtos.request.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    private Integer categoryId;
    @NotNull
    private Integer modelId;
}
