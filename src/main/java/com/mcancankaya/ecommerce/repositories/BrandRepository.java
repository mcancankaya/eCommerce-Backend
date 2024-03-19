package com.mcancankaya.ecommerce.repositories;

import com.mcancankaya.ecommerce.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsBrandByName(String name);
}
