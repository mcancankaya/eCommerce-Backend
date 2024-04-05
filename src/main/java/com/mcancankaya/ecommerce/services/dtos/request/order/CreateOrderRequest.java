package com.mcancankaya.ecommerce.services.dtos.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private List<OrderItemRequest> orderItems;
}
