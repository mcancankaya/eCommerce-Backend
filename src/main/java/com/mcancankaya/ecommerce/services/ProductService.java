package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Product;
import com.mcancankaya.ecommerce.repositories.OrderItemRepository;
import com.mcancankaya.ecommerce.repositories.ProductRepository;
import com.mcancankaya.ecommerce.services.dtos.request.product.CreateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.request.product.UpdateProductRequest;
import com.mcancankaya.ecommerce.services.dtos.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final OrderItemRepository orderItemRepository;
    private final static String PATH = "C:\\Workspaces\\mcancankaya\\ecommerce-project\\frontend\\ecommerce-ui\\imgs\\";

    private static boolean productIsActive(Product product) {
        return product.getIsActive();
    }

    public CustomResponse<ProductResponse> create(MultipartFile file, CreateProductRequest request) {
        String imageUrl = saveProductImage(file);
        Product product = modelMapperService.forRequest().map(request, Product.class);
        product.setIsActive(true);
        product.setImageUrl(imageUrl);

        Product savedProduct = productRepository.save(product);
        ProductResponse productResponse = modelMapperService.forResponse().map(savedProduct, ProductResponse.class);
        return new CustomResponse<>(productResponse, "Ürün oluşturuldu.");
    }

    public CustomResponse<ProductResponse> update(UpdateProductRequest request) {
        Product product = modelMapperService.forRequest().map(request, Product.class);
        Product updatedProduct = productRepository.save(product);
        ProductResponse updatedProductResponse = modelMapperService.forResponse().map(updatedProduct, ProductResponse.class);
        return new CustomResponse<>(updatedProductResponse, "Ürün güncellendi.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        boolean isOrdered = orderItemRepository.existsByProductId_Id(id);
        if (!isOrdered) {
            productRepository.deleteById(id);
            return new CustomResponse<>("Ürün silindi.");
        } else {
            return new CustomResponse<>("Ürün sipariş nedeniyle silinemedi.", false);
        }
    }

    public CustomResponse<List<ProductResponse>> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = products.stream().map(product -> modelMapperService.forResponse().map(product, ProductResponse.class)).toList();
        return new CustomResponse<>(productResponseList, "Ürünler listelendi.");
    }

    public CustomResponse<ProductResponse> getById(Integer id) {
        ProductResponse productResponse = modelMapperService.forResponse().map(productRepository.findById(id), ProductResponse.class);
        return new CustomResponse<>(productResponse, "Ürün Id'e göre listelendi.");
    }

    public CustomResponse<List<ProductResponse>> getFilterByModelIdAndCategoryId(Integer modelId, Integer categoryId) {
        List<Product> products;

        if (categoryId != 0) products = productRepository.findAllByModel_IdAndCategory_Id(modelId, categoryId);
        else products = productRepository.findAllByModel_Id(modelId);
        List<ProductResponse> productResponseList = products.stream().map(product -> modelMapperService.forResponse().map(product, ProductResponse.class)).toList();
        return new CustomResponse<>(productResponseList, "Ürünler Model ve Kategoriye göre filtrelendi.");
    }

    public CustomResponse<List<ProductResponse>> getFilterByCategoryId(Integer categoryId) {
        List<Product> products = productRepository.findAllByCategory_Id(categoryId);
        List<ProductResponse> productResponseList = products.stream().map(product -> modelMapperService.forResponse().map(product, ProductResponse.class)).toList();
        return new CustomResponse<>(productResponseList, "Ürünler Kategoriye göre filtrelendi.");
    }

    private String saveProductImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path uploadPath = Path.of(PATH);
        Path filePath;
        String newPath;
        try {
            Files.createDirectories(uploadPath);
            newPath = uploadPath.resolve(fileName).toString();
            newPath = (newPath.substring(newPath.indexOf("imgs"), newPath.length()));
            filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newPath;
    }
}
