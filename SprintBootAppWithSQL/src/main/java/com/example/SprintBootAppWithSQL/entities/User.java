package com.example.SprintBootAppWithSQL.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user") // Renaming the table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date dateJoined;
    private Date lastLogin;
    private Boolean isActive;
    private Boolean isLocked;
    private String profilePicture;
    private String bio;
    private String primaryPhone;
    private String secondaryPhone;
    private String workPhone;


    @Transient
    private List<Long> rolesId;

    //Use a Data Transfer Object (DTO) to return only the fields you need without triggering lazy loading of relationships.
    //Use @JsonIgnore to ignore the userRoles field during JSON serialization, which will prevent lazy loading.
    @JsonIgnore
    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "UserRoles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;

}
