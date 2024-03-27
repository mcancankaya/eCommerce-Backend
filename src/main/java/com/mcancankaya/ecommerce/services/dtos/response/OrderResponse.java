package com.mcancankaya.ecommerce.services.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private Integer id;
    private UserResponse user;
    private List<OrderItemResponse> orderItems;
    private BigDecimal totalAmount;
}
