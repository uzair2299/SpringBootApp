package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeaveType {
    @Id
    private Long id;
    private String name;
    // other leave type properties

    @OneToMany(mappedBy = "leaveType")
    private List<EmployeeLeaveBalance> leaveBalances;
}
