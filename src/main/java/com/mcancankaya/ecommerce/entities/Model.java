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
@Table(name = "models", schema = "ecommerce")
public class Model {
    @Id
    @GeneratedValue(generator = "model_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "model_id_generator", sequenceName = "model_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
