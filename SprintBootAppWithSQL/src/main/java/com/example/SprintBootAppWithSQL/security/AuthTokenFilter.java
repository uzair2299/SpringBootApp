package com.example.SprintBootAppWithSQL.security;

import com.example.SprintBootAppWithSQL.services.UserDetailsServiceImpl;
import com.example.SprintBootAppWithSQL.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtils jwtUtils;


    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get the authorization header from the request
        String authHeader = request.getHeader(AUTH_HEADER);

        // If the header is null or doesn't start with "Bearer ", continue with the filter chain
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the header
        String jwtToken = authHeader.substring(TOKEN_PREFIX.length());


        if (jwtUtils.validateJwtToken(jwtToken)) {
            // Get the user details from the token
            UserDetails userDetails = userDetailsService.loadUserByUsername("ABC");

            // Set the authentication object in the security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
