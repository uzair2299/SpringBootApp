package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    private String permissionName;

    private String description;
//    @ManyToMany(mappedBy = "permissions")
//    private List<Resource> resources;

}
