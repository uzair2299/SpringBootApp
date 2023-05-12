package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date dateJoined;
    private Date lastLogin;
    private Boolean isActive;
    private Boolean isLocked;
    private String profilePicture;
    private String bio;
    private String primaryPhone;
    private String secondaryPhone;
    private String workPhone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "employee")
    private List<Document> documents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeEmergencyContact> emergencyContacts;

    @ManyToOne
    @JoinColumn(name="work_type_id")
    private WorkType workType;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeLeaveBalance> leaveBalances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeLeaveRequest> leaveRequests;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Increment> increments;

    @ManyToOne
    @JoinColumn(name = "designation_Id")
    private Designation designation;
}
