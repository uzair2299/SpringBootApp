package com.example.SprintBootAppWithSQL.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    private String description;
    @Column(name = "created_at",nullable = true)
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @Column(name = "is_Deleted")
    private boolean isDeleted;


    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles = new HashSet<>();


//    @OneToMany(mappedBy = "role")
//    private Set<RolesMenu> rolesMenus = new HashSet<>();


//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
}
