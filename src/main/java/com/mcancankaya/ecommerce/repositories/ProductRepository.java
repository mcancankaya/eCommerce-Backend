package com.mcancankaya.ecommerce.repositories;

import com.mcancankaya.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByModel_IdAndCategory_Id(Integer modelId, Integer categoryId);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findAllByModel_Id(Integer modelId);

    List<Product> findAllByCategory_Id(Integer category);
}
