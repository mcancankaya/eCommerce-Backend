package com.mcancankaya.ecommerce.entities;

import com.mcancankaya.ecommerce.core.security.CustomUser;
import com.mcancankaya.ecommerce.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "ecommerce")
public class User {
    @Id
    @GeneratedValue(generator = "user_id_generator")
    @SequenceGenerator(schema = "ecommerce", name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "roles")
    private List<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

}
