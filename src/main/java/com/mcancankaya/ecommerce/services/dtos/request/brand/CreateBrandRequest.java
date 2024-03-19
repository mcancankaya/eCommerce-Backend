package com.mcancankaya.ecommerce.services.dtos.request.brand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateBrandRequest {
    @NotEmpty
    @Min(2)
    private String name;
}
