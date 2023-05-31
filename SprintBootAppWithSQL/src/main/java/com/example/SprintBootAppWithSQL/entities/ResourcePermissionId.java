package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ResourcePermissionId implements Serializable {

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "permission_id")
    private Long permissionId;

    // Define constructors, equals(), hashCode(), and getters/setters
}

