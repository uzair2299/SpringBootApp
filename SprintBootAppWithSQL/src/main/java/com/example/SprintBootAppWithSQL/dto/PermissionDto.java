package com.example.SprintBootAppWithSQL.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class PermissionDto {
    private Long id;
    private String permissionName;
    private String code;
    private String module;
    private Long createdAt;
    private Long updatedAt;
    private String description;
}
