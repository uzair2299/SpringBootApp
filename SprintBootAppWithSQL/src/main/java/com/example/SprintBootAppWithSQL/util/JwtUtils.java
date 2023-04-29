package com.example.SprintBootAppWithSQL.util;

import com.example.SprintBootAppWithSQL.props.jwtProperties;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Autowired
    jwtProperties jwtProperties;
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtProperties.getCookieName());
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }


    public boolean validateJwtToken(String authToken,HttpServletResponse response) throws IOException {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getPrivateKey()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {

            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
