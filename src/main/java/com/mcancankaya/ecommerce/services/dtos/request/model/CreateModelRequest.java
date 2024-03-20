package com.mcancankaya.ecommerce.services.dtos.request.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateModelRequest {
    private Integer modelId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Integer brandId;
}
