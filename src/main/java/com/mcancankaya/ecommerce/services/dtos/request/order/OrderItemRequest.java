package com.mcancankaya.ecommerce.services.dtos.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
}
