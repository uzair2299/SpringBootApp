package com.example.SprintBootAppWithSQL.dto;


import jakarta.persistence.Entity;
import lombok.*;

@Data
public class UserDto {
    private String userName;
    private String password;
}
