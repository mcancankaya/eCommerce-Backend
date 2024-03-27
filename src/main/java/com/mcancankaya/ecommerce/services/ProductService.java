package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Product;
import com.mcancankaya.ecommerce.repositories.ProductRepository;
import com.mcancankaya.ecommerce.services.dtos.request.product.CreateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.request.product.UpdateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;

    private static boolean productIsActive(Product product) {
        return product.getIsActive();
    }

    public CustomResponse<ProductResponse> create(CreateProductRequest request) {
        Product product = modelMapperService.forRequest().map(request, Product.class);
        product.setIsActive(true);

        Product savedProduct = productRepository.save(product);
        ProductResponse productResponse = modelMapperService.forResponse().map(savedProduct, ProductResponse.class);
        return new CustomResponse<>(productResponse, "Product created successfully.");
    }

    public CustomResponse<ProductResponse> update(UpdateProductRequest request) {
        Product product = modelMapperService.forRequest().map(request, Product.class);
        Product updatedProduct = productRepository.save(product);
        ProductResponse updatedProductResponse = modelMapperService.forResponse().map(updatedProduct, ProductResponse.class);
        return new CustomResponse<>(updatedProductResponse, "Product updated.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        productRepository.deleteById(id);
        return new CustomResponse<>("Product deleted.");
    }

    public CustomResponse<List<ProductResponse>> getAll() {
        List<Product> products = productRepository.findAll().stream().filter(ProductService::productIsActive).toList();
        List<ProductResponse> productResponseList = products.stream().map(product -> modelMapperService.forResponse().map(product, ProductResponse.class)).toList();
        return new CustomResponse<>(productResponseList, "Products listed.");
    }

    public CustomResponse<ProductResponse> getById(Integer id) {
        ProductResponse productResponse = modelMapperService.forResponse().map(productRepository.findById(id), ProductResponse.class);
        return new CustomResponse<>(productResponse, "Product listed.");
    }
}
