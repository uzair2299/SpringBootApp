package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.JwtDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.services.UserService;
import com.example.SprintBootAppWithSQL.services.jwt.jwtImpl;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    jwtImpl jwt;

    @Autowired
    UserService userService;


    //TODO
    // need to handle
    // AccountExpiredException - This exception is thrown when the user account has expired.
    // CredentialsExpiredException - This exception is thrown when the user's credentials have expired and need to be updated.
    // AuthenticationCredentialsNotFoundException - This exception is thrown when the authentication credentials are missing (e.g. the user didn't provide a password).
    // AuthenticationServiceException - This exception is thrown when there is an error in the authentication service (e.g. the user database is down).


    @PostMapping("/api/v1/login/")
    public ResponseEntity<JwtDto> login(@RequestBody UserDto user) {
        try {
            UserDto result = userService.getUserByUserName(user);

            return new ResponseEntity<>(jwt.createToken(), HttpStatus.OK);
        } catch (BadCredentialsException | DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtDto().error(e.getMessage()));
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(new JwtDto().error(e.getMessage()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JwtDto().error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
