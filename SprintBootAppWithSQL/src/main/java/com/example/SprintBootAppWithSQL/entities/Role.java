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
@NamedStoredProcedureQuery(
        name = "GetUserById",
        procedureName = "GetEmployeesByDepartment"
)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String description;
    private Date createdAt;
    private Date updatedAt;


    @OneToMany(mappedBy = "role")
    private Set<UserRoles> userRoles = new HashSet<>();


    @OneToMany(mappedBy = "role")
    private Set<RolesMenu> rolesMenus = new HashSet<>();


//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
}
