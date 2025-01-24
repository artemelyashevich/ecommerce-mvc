package com.elyashevich.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }
}