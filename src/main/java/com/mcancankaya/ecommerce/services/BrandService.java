package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Brand;
import com.mcancankaya.ecommerce.repositories.BrandRepository;
import com.mcancankaya.ecommerce.services.dtos.request.brand.UpdateBrandRequest;
import com.mcancankaya.ecommerce.services.dtos.response.BrandResponse;
import com.mcancankaya.ecommerce.services.dtos.request.brand.CreateBrandRequest;
import com.mcancankaya.ecommerce.services.rules.BrandRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;
    private final BrandRuleService brandRuleService;

    public CustomResponse<BrandResponse> create(CreateBrandRequest request) {
        brandRuleService.brandIsAvailable(request.getName());

        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        Brand savedBrand = brandRepository.save(brand);
        BrandResponse savedBrandResponse = modelMapperService.forResponse().map(savedBrand, BrandResponse.class);
        return new CustomResponse<>(savedBrandResponse, "Marka Oluşturuldu.");
    }

    public CustomResponse<BrandResponse> update(UpdateBrandRequest request) {
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        Brand updatedBrand = brandRepository.save(brand);
        BrandResponse updatedBrandResponse = modelMapperService.forResponse().map(updatedBrand, BrandResponse.class);
        return new CustomResponse<>(updatedBrandResponse, "Marka Güncellendi.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        brandRepository.deleteById(id);
        return new CustomResponse<>("Marka Silindi.");
    }

    public CustomResponse<List<BrandResponse>> getAll() {
        List<Brand> brandList = brandRepository.findAll();
        List<BrandResponse> brandResponses = brandList.stream().map((brand) -> modelMapperService.forResponse().map(brand, BrandResponse.class)).collect(Collectors.toList());
        return new CustomResponse<>(brandResponses, "Marakalar Listelendi.");
    }

    public CustomResponse<BrandResponse> getById(Integer id) {
        BrandResponse response = modelMapperService.forResponse().map(brandRepository.findById(id), BrandResponse.class);
        return new CustomResponse<>(response, "Marka Id'e göre listelendi.");
    }
}
