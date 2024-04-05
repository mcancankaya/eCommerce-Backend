package com.mcancankaya.ecommerce.controllers;

import com.mcancankaya.ecommerce.services.ProductService;
import com.mcancankaya.ecommerce.services.dtos.request.product.CreateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.request.product.UpdateProductRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping(path = "/filter", params = {"model", "category"})
    public ResponseEntity<?> getFilterByCategoryAndModel(@RequestParam Integer model, @RequestParam Integer category) {
        return ResponseEntity.ok(productService.getFilterByModelIdAndCategoryId(model, category));
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(productService.getFilterByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart("file") MultipartFile file, @RequestPart("product") @Valid CreateProductRequest request) {
        return ResponseEntity.ok(productService.create(file, request));
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
