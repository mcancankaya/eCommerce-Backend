package com.mcancankaya.ecommerce.services.dtos.auth;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotNull
    @Min(2)
    private String firstName;
    @NotNull
    @Min(2)
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    @Min(8)
    @Max(12)
    private String password;
}
