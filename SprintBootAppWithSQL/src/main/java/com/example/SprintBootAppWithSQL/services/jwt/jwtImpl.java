package com.example.SprintBootAppWithSQL.services.jwt;

import com.example.SprintBootAppWithSQL.controller.UserController;
import com.example.SprintBootAppWithSQL.props.jwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class jwtImpl implements jwt {

    Logger logger = LoggerFactory.getLogger(jwtImpl.class);
    @Autowired
    jwtProperties jwtProperties;

    @Override
    public String createToken() {
        logger.info("jwt private key " + jwtProperties.getPrivateKey());
        logger.info("jwt audience " + jwtProperties.getAudience());
        logger.info("jwt issuer " + jwtProperties.getIssuer());
        logger.info("jwt expiry " + jwtProperties.getExpiry());
        String jwtToken = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setAudience(jwtProperties.getAudience())
                .setIssuer(jwtProperties.getIssuer())
                .setExpiration(new Date((new Date()).getTime() + jwtProperties.getExpiry()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getPrivateKey())
                .compact();
        logger.info("jwt token - " + jwtToken);
        return null;
    }
}
