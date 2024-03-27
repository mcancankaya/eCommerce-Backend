package com.mcancankaya.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items", schema = "ecommerce")
public class OrderItem {
    @Id
    @GeneratedValue(generator = "order_item_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "order_item_id_generator", sequenceName = "order_item_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product productId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;
}
