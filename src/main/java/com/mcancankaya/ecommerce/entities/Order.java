package com.mcancankaya.ecommerce.entities;

import com.mcancankaya.ecommerce.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "ecommerce")
public class Order {
    @Id
    @GeneratedValue(generator = "order_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
}
