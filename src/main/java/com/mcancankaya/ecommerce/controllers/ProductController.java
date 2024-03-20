package com.mcancankaya.ecommerce.controllers;

import com.mcancankaya.ecommerce.services.ProductService;
import com.mcancankaya.ecommerce.services.dtos.request.product.CreateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.request.product.UpdateProductRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@NotNull Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UpdateProductRequest request) {
        return ResponseEntity.ok(productService.update(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }
}
