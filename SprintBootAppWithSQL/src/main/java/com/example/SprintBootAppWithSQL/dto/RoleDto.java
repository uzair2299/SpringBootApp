package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data

public class RoleDto {
    private Long id;
    private String roleName;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
