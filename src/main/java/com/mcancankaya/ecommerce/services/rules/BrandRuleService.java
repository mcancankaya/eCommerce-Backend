package com.mcancankaya.ecommerce.services.rules;

import com.mcancankaya.ecommerce.core.exceptions.BusinessException;
import com.mcancankaya.ecommerce.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandRuleService {
    private final BrandRepository brandRepository;

    public void brandIsAvailable(String name) {
        boolean isAvailable = brandRepository.existsBrandByName(name);
        if (isAvailable) {
            throw new BusinessException("Brand name is already available.");
        }

    }
}
