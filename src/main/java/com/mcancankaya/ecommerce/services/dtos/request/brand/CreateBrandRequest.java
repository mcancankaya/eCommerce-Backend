package com.mcancankaya.ecommerce.services.dtos.request.brand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBrandRequest {
    @NotEmpty
    @Size(min = 2)
    private String name;
}
