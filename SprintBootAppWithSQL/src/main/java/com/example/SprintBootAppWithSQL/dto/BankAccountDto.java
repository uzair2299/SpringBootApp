package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class BankAccountDto {
    private Long id;
    private String accountNumber;
    private String routingNumber;
    private Employee employee;
}
