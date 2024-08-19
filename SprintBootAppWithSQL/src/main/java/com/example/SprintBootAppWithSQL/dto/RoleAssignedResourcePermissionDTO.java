package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAssignedResourcePermissionDTO {
    private RoleDto roles;
}
