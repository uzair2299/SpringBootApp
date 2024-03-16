package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 255, nullable = false)
    private String permissionName;
    @Column(length = 100, unique = true)
    private String code;

    @Column(length = 255)
    private String module;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(columnDefinition = "TEXT")
    private String description;
//    @ManyToMany(mappedBy = "permissions")
//    private List<Resource> resources;


    public Permission(String permissionName,String description){
        this.permissionName = permissionName;
        this.description = description;
    }
    // Default constructor (required by JPA)
    public Permission() {
    }
}
