package com.example.SprintBootAppWithSQL.services.jwt;

import com.example.SprintBootAppWithSQL.dto.JwtDto;

import java.util.Map;

public interface jwt {
    JwtDto createToken(Map<String,Object> claims);
}
