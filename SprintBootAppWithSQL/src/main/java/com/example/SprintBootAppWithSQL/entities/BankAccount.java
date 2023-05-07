package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "routing_number")
    private String routingNumber;

    @ManyToOne
    private Employee employee;

}
