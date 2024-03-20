package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Category;
import com.mcancankaya.ecommerce.repositories.CategoryRepository;
import com.mcancankaya.ecommerce.services.dtos.request.category.CreateCategoryRequest;
import com.mcancankaya.ecommerce.services.dtos.request.category.UpdateCategoryRequest;
import com.mcancankaya.ecommerce.services.dtos.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;

    public CustomResponse<CategoryResponse> create(CreateCategoryRequest request) {
        Category category = modelMapperService.forRequest().map(request, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryResponse savedCategoryResponse = modelMapperService.forResponse().map(savedCategory, CategoryResponse.class);
        return new CustomResponse<>(savedCategoryResponse, "Category created.");
    }

    public CustomResponse<CategoryResponse> update(UpdateCategoryRequest request) {
        Category category = modelMapperService.forRequest().map(request, Category.class);
        Category updatedCategory = categoryRepository.save(category);
        CategoryResponse updatedCategoryResponse = modelMapperService.forResponse().map(updatedCategory, CategoryResponse.class);
        return new CustomResponse<>(updatedCategoryResponse, "Category updated.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        categoryRepository.deleteById(id);
        return new CustomResponse<>("Category deleted.");
    }

    public CustomResponse<List<CategoryResponse>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream().map(category -> modelMapperService.forResponse().map(category, CategoryResponse.class)).toList();
        return new CustomResponse<List<CategoryResponse>>(categoryResponses, "Categories Listed.");
    }

    public CustomResponse<CategoryResponse> getById(Integer id) {
        CategoryResponse categoryResponse = modelMapperService.forResponse().map(categoryRepository.findById(id), CategoryResponse.class);
        return new CustomResponse<CategoryResponse>(categoryResponse, "Category listed.");
    }
}
