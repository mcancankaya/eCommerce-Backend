package com.mcancankaya.ecommerce.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Integer id;
    private ProductResponse product;
    private Integer quantity;
}
