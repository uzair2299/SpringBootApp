package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class WorkTypeDto {
    private Long id;
    private String name;
}
