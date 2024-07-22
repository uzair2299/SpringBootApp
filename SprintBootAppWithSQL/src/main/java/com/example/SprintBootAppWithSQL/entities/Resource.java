package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @Column(nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String resourceEndpoint;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String methodType;  // GET, POST, PUT, DELETE, etc.

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private boolean isAuthRequired;

    @Column(nullable = true)
    private Integer rateLimit;

    @Column(nullable = false)
    private boolean isDeprecated;

    @Column(nullable = true)
    private String documentationUrl;

    @Column(nullable = true)
    private String owner;



//    @ManyToMany
//    @JoinTable(
//            name = "resource_permissions",
//            joinColumns = @JoinColumn(name = "resource_id"),
//            inverseJoinColumns = @JoinColumn(name = "permission_id")
//    )
//    private List<Permission> permissions;

}
