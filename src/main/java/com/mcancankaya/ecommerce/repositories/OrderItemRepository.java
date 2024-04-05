package com.mcancankaya.ecommerce.repositories;

import com.mcancankaya.ecommerce.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    void deleteByOrderId(Integer id);

    boolean existsByProductId_Id(Integer productId);
}
