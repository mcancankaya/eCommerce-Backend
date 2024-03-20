package com.mcancankaya.ecommerce.services.dtos.request.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModelRequest {
    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 2)
    @NotBlank
    private String modelName;
    @NotNull
    private Integer brandId;
}
