package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.BankAccount;
import com.example.SprintBootAppWithSQL.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

