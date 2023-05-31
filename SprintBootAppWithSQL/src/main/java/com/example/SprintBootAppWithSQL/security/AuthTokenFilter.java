package com.example.SprintBootAppWithSQL.security;

import com.example.SprintBootAppWithSQL.services.UserDetailsImpl;
import com.example.SprintBootAppWithSQL.services.UserDetailsServiceImpl;
import com.example.SprintBootAppWithSQL.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

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

        String requestMethod = request.getMethod();
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String scheme = request.getScheme(); // http or https
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        logger.info(String.format("authHeader - [%s]", authHeader));
        logger.info(String.format("requestURL - [%s]", requestURL));
        logger.info(String.format("requestURI - [%s]", requestURI));
        logger.info(String.format("requestMethod - [%s]", requestMethod));
        logger.info(String.format("scheme - [%s]", scheme));
        logger.info(String.format("serverName - [%s]", serverName));
        logger.info(String.format("serverPort - [%s]", serverPort));
        logger.info(String.format("contextPath - [%s]", contextPath));

        StringBuilder baseFQDN = new StringBuilder();
        baseFQDN.append(scheme).append("://").append(serverName);
        baseFQDN.append(":").append(serverPort);
        baseFQDN.append(contextPath);
        logger.info(String.format("baseFQDN", baseFQDN));


        if (isLoginRequest(requestURL, requestURI)) {
            filterChain.doFilter(request, response);
            //The return; statement immediately exits the current method and returns from the filter. This ensures that the remaining lines of code in the filter method are not executed for the login request.
            return;
        }

        // If the header is null or doesn't start with "Bearer ", continue with the filter chain
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            //response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            //response.getWriter().write("Access is forbidden");
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the header
        String jwtToken = authHeader.substring(TOKEN_PREFIX.length());


        if (jwtUtils.validateJwtToken(jwtToken, response)) {
            // Get the user details from the token
            String userName = jwtUtils.extractClaims(jwtToken).get("userName", String.class);

            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername("ABC");


            // Set the authentication object in the security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Check if the user has the required authority based on request method and URI
        if (!hasAuthority(requestMethod, requestURI)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token has expired");
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthority(String requestMethod, String requestURI) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

            String username = principal.getUsername();
            String password = principal.getPassword();

            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                    authentication.getAuthorities();
            //        // Check if the user has the required authority based on request method and URI
            if (requestMethod.equalsIgnoreCase("GET") && authorities.contains(new SimpleGrantedAuthority("read"))) {
                return true;
            } else if ((requestMethod.equalsIgnoreCase("POST") || requestMethod.equalsIgnoreCase("PUT") ||
                    requestMethod.equalsIgnoreCase("DELETE")) &&
                    authorities.contains(new SimpleGrantedAuthority("write"))) {
                return true;
            }
        }


        return true;
    }

    private boolean isLoginRequest(String requestURL, String requestURI) {
        logger.info(String.format("Entering AuthTokenFilter.isLoginRequest() - requestURL: [%s], requestURI: [%s]", requestURL, requestURI));
        // Check the request URL or URI for login-specific patterns
        // You can customize this method based on your application's login URL patterns
        //For example, if the URL of the current request is https://www.example.com/products?id=123, then:
        //getRequestURL() would return: https://www.example.com/products
        //getRequestURI() would return: /products
        //It's important to note that the URL represents the complete address of a resource, while the URI represents the path or identifier that can be used to locate a resource.
        //You can check the HTTP method of the request to identify if it corresponds to a login action. For example, login requests often use the POST method to submit the login
        //Login requests often include specific parameters, such as username and password. You can examine the request parameters to identify if they match the parameters typically used for login.
        //Login requests may include specific headers or header values that indicate they are related to authentication or login. You can inspect the request headers using request.getHeader("headerName") and check for any login-related headers or specific header values.
        return requestURL.contains("/login") || requestURI.contains("/login");
    }
}

/*
In the debug mode, when you inspect an interface type variable, you might see the fields of the implementing class because the debugging tool often shows the actual runtime type of the object being referenced.
 */