package com.example.SprintBootAppWithSQL.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@Component
public class JwtDto {
    private String token;
    private String refreshToken;
    private String error;

    List<MenuDto> menuList = new ArrayList<>();

    public JwtDto token(String token) {
        this.token = token;
        return this;
    }

    public JwtDto refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public JwtDto error(String error) {
        this.error = error;
        return this;
    }
}
