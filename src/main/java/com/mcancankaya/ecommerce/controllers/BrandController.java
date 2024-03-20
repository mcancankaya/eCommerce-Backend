package com.mcancankaya.ecommerce.controllers;

import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.services.BrandService;
import com.mcancankaya.ecommerce.services.dtos.request.brand.UpdateBrandRequest;
import com.mcancankaya.ecommerce.services.dtos.request.brand.CreateBrandRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomResponse<?>> create(@RequestBody @Valid CreateBrandRequest request) {
        return ResponseEntity.ok(brandService.create(request));
    }

    @PutMapping
    public ResponseEntity<CustomResponse<?>> update(@RequestBody @Valid UpdateBrandRequest request) {
        return ResponseEntity.ok(brandService.update(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse<?>> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(brandService.deleteById(id));
    }
}
