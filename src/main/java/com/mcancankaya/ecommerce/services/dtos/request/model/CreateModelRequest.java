package com.mcancankaya.ecommerce.services.dtos.request.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateModelRequest {
    private Integer modelId;
    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;
    @NotNull
    private Integer brandId;
}
