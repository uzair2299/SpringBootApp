package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resources")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "resource_name",nullable = false)

    private String resourceName;

    @Column(name="resource_endpoint",nullable = false)
    private String resourceEndpoint;

    @Column(name = "version",nullable = false)

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
