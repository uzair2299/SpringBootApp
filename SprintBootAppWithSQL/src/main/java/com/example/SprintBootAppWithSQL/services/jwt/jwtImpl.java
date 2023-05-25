package com.example.SprintBootAppWithSQL.services.jwt;

import com.example.SprintBootAppWithSQL.dto.JwtDto;
import com.example.SprintBootAppWithSQL.props.jwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class jwtImpl implements jwt {

    Logger logger = LoggerFactory.getLogger(jwtImpl.class);
    @Autowired
    jwtProperties jwtProperties;
    @Autowired JwtDto jwtDto;

    @Override
    public JwtDto createToken(Map<String,Object> claims) {
        logger.info("jwt private key " + jwtProperties.getPrivateKey());
        logger.info("jwt audience " + jwtProperties.getAudience());
        logger.info("jwt issuer " + jwtProperties.getIssuer());
        logger.info("jwt expiry " + jwtProperties.getExpiry());
        long expire  = (jwtProperties.getExpiry())/1000*60*60;
        String jwtToken = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setAudience(jwtProperties.getAudience())
                .setIssuer(jwtProperties.getIssuer())
                .setExpiration(new Date((new Date()).getTime() + expire))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getPrivateKey())
                .compact();
        logger.info("jwt token - " + jwtToken);

        String refreshToken = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExpiry()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getPrivateKey())
                .compact();

        jwtDto.setToken(jwtToken);
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }
}
