package com.mcancankaya.ecommerce.services.dtos.request.category;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 2)
    private String name;
}
