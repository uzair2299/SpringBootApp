package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
    private Date salaryStartDate;
    private Date salaryEndDate;
    private double salaryAmount;
}
