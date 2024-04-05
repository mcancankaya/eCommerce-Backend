package com.mcancankaya.ecommerce.services.dtos.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
