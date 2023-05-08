package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    private Date payrollStartDate;
    private Date payrollEndDate;
    private String payPeriod;
    private double grossPay;
    private double netPay;
    private double federalTaxWithholding;
    private double stateTaxWithholding;
    private double socialSecurityTaxWithholding;
    private double medicareTaxWithholding;
    private double otherDeductions;
}
