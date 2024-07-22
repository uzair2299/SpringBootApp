package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDto {
    private Long id;
    private String permissionName;
    private String code;
    private String module;
    private boolean isDeleted;
    private Long createdAt;
    private Long updatedAt;
    private String description;
}
