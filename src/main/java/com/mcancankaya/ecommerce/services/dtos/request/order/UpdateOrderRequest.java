package com.mcancankaya.ecommerce.services.dtos.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderRequest {
    @NotNull
    private Integer orderId;
    private List<OrderItemRequest> orderItems;

}
