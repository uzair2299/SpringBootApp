package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "product_grade_id")
    private ProductGrade productGrade;


    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetail;
}
