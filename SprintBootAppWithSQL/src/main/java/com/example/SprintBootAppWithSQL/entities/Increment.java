package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Increment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incrementId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    private Date incrementDate;
    private double incrementAmount;

}
