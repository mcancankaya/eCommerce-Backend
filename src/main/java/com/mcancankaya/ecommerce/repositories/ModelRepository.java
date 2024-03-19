package com.mcancankaya.ecommerce.repositories;

import com.mcancankaya.ecommerce.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
