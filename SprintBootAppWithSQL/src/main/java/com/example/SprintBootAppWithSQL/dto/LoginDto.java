package com.example.SprintBootAppWithSQL.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
public class LoginDto {
    @NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Username is required")
    private String password;
    private String email;
    private long id;

}
