package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeDto {
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
    private long designation_id;
    private long work_type_id;
    private long department_id;

    private DepartmentDto department;

    private List<BankAccount> bankAccounts;

    private List<Document> documents;

    private List<EmployeeEmergencyContact> emergencyContacts;

    private WorkTypeDto workType;

    private List<EmployeeLeaveBalance> leaveBalances;

    private List<EmployeeLeaveRequest> leaveRequests;

    private List<Salary> salaries;

    private List<Payroll> payrolls;

    private List<Increment> increments;

    private DesignationDto designation;
}
