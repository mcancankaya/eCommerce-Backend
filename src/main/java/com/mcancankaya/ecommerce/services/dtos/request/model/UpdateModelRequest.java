package com.mcancankaya.ecommerce.services.dtos.request.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Min(1)
    @NotBlank
    private String modelName;
    @NotNull
    private Integer brandId;
}
