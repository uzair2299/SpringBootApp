package com.example.SprintBootAppWithSQL.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ErrorResponseDto {
    private String message;
    Map<String, String> errors;
    private int statusCode;
}
