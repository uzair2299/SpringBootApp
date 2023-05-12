package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DepartmentDto {
    private Long id;

    private String name;

    private String description;

    private int employeeCount;

    private String phoneNumber;

    private String emailAddress;

    private String notes;

    private LocalDate startDate;

    private LocalDate endDate;

    //@Enumerated(EnumType.STRING)
    //@Column(name = "status")
    //private DepartmentStatus status;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "manager_id")
    //private Employee manager;
}
