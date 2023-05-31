package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resource_permissions")
public class ResourcePermission {

    @EmbeddedId
    private ResourcePermissionId resourcePermissionId;

    @Column(name = "allowed_methods")
    private String allowedMethods;

    // Define other fields and relationships

    @ManyToOne
    @JoinColumn(name = "resource_id", insertable = false, updatable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
    private Permission permission;
    // Define getters and setters
}

