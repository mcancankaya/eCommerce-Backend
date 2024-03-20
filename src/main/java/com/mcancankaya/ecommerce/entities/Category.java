package com.mcancankaya.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", schema = "ecommerce")
public class Category {
    @Id
    @GeneratedValue(generator = "category_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "category_id_generator", sequenceName = "category_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;
}
