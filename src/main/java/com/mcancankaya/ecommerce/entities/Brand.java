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
@Table(name = "brands", schema = "ecommerce")
public class Brand {
    @Id
    @GeneratedValue(generator = "brand_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "brand_id_generator", sequenceName = "brand_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Model> models;

    @Column(name = "logo")
    private byte[] logo;
}
