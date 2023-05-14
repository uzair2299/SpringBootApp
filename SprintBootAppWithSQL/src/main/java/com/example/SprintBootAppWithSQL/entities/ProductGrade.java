package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

//    @OneToMany(mappedBy = "productType")
//    private List<Product> products;

    @OneToMany(mappedBy = "productGrade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

}
