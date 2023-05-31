package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    private String resourceName;

    private String resourceEndpoint;

//    @ManyToMany
//    @JoinTable(
//            name = "resource_permissions",
//            joinColumns = @JoinColumn(name = "resource_id"),
//            inverseJoinColumns = @JoinColumn(name = "permission_id")
//    )
//    private List<Permission> permissions;

}
