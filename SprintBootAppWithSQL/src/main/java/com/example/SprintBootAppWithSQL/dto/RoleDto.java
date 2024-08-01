package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String roleName;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String endPoint;
    private List<Long> roleIds;
}
