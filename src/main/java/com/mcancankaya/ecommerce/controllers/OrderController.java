package com.mcancankaya.ecommerce.controllers;

import com.mcancankaya.ecommerce.services.OrderService;
import com.mcancankaya.ecommerce.services.dtos.request.order.CreateOrderRequest;
import com.mcancankaya.ecommerce.services.dtos.request.order.UpdateOrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(orderService.getByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.update(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.deleteById(id));
    }
}
