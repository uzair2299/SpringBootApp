package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date dateJoined;
    private Date lastLogin;
    private Boolean isActive;
    private String profilePicture;
    private String bio;
    private String primaryPhone;
    private String secondaryPhone;
    private String workPhone;
    @Transient
    private List<Long> rolesId;

    @ManyToMany
    @JoinTable(
            name = "UserRoles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
