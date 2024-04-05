package com.mcancankaya.ecommerce.services.dtos.request.brand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandRequest {

    @NotNull
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;
}
