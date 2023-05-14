package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String salesRep;
    //private PaymentTerms paymentTerms;
    private double creditLimit;
    //private List<Order> orderHistory;
    //private ShippingPreferences shippingPreferences;
}
