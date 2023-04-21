package com.example.SprintBootAppWithSQL.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Setter
@Getter
@Component
public class JwtDto {
    private String token;
}
