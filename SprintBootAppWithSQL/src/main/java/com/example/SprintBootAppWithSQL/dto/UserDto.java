package com.example.SprintBootAppWithSQL.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private String email;
    private Long dateJoined;
    private Long lastLogin;
    private Boolean isActive;
    private Boolean isLocked;
    private String profilePicture;
    private String bio;
    private String primaryPhone;
    private String secondaryPhone;
    private String workPhone;
    private List<RoleDto> roles = new ArrayList<>();
}
