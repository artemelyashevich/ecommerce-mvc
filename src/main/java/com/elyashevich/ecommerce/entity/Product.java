package com.elyashevich.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "image")
    private String image;

    @Builder.Default
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Cart> carts = new ArrayList<>();

    @Version
    private int version;
}