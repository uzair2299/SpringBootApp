package com.example.SprintBootAppWithSQL.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class PasswordResetDto {
    private Long id;
    private String newPassword;
    private String confirmPassword;
}