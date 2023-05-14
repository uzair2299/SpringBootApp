package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private List<ProductGrade> productGrades;



    @OneToMany(mappedBy = "productType")
    private List<Product> products;
}
