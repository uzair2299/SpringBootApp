package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.*;

@Data
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String roleName;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    private String endPoint;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    private List<Long> roleIds;
    private List<ResourceDto> resources = new ArrayList<>();
}
