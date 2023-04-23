package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.JwtDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.services.jwt.jwtImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestingController {
    Logger logger = LoggerFactory.getLogger(TestingController.class);
    @Autowired
    jwtImpl jwt;
    @GetMapping("/api/test/")
    @ResponseBody
    public ResponseEntity<String> createUser() {
        try {
            //userRepository.save(user);
            return new ResponseEntity<>("Hello Testing", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
