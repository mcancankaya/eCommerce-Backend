package com.mcancankaya.ecommerce.services.dtos.request.category;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3)
    private String name;
}
