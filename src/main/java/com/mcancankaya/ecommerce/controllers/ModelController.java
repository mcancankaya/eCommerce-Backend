package com.mcancankaya.ecommerce.controllers;

import com.mcancankaya.ecommerce.services.ModelService;
import com.mcancankaya.ecommerce.services.dtos.request.model.CreateModelRequest;
import com.mcancankaya.ecommerce.services.dtos.request.model.UpdateModelRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/model")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(modelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateModelRequest request) {
        return ResponseEntity.ok(modelService.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UpdateModelRequest request) {
        return ResponseEntity.ok(modelService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(modelService.deleteById(id));
    }
}
