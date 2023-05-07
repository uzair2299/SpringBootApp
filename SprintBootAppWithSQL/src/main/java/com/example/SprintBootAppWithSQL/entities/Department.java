package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "employee_count")
    private int employeeCount;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "notes")
    private String notes;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    //@Enumerated(EnumType.STRING)
    //@Column(name = "status")
    //private DepartmentStatus status;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "manager_id")
    //private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;


    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
