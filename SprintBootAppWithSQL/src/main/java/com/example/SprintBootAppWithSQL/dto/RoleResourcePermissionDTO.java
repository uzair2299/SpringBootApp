package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResourcePermissionDTO {
    private Long rRoleId;                    // Role ID from 'roles'
    private String roleName;                // Role name from 'roles'
    private Long rrpRoleId;                 // Role ID from 'roles_resources_permissions'
    private Long rrpResourcesPermissionsId; // Resources permissions ID from 'roles_resources_permissions'
    private Long rpId;                      // Resources permissions ID from 'resources_permissions'
    private Long rpResourceId;              // Resource ID from 'resources_permissions'
    private Long rpPermissionId;            // Permission ID from 'resources_permissions'
    private String permissionName;          // Permission name from 'permission'
    private Long pId;                       // Permission ID from 'permission'
    private Long reId;                      // Resource ID from 'resources'
    private String resourceName;            // Resource name from 'resources'
    private String methodType;              // Method type from 'resources'
    private String resourceEndpoint;        // Resource endpoint from 'resources// '
    private Long resourcesPermissionsId;
}
