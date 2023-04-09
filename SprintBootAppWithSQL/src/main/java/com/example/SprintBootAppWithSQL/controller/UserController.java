package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/api/v1/users")
    @ResponseBody
    public String getUsers(){
        return "going to return all users";
    }
}
