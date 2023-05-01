package com.example.SprintBootAppWithSQL.dto;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Data
public class UserDto {
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
}
