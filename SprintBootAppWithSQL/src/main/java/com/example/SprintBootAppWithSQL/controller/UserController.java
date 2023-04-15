package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping("/api/v1/users")
    public ResponseEntity<List<User>> getUsers() {
        logger.info(String.format("Executing getUser request"));
        logger.debug(String.format("Executing getUser request"));
        logger.error(String.format("Executing getUser request"));
        List<User> userList = new ArrayList<>();
        User user = new User(1,"ABC");
        User user1 = new User(2,"ABC");
        userList.add(user1);
        userList.add(user);
        //userList = userService.getAllUsers();
        return ResponseEntity.accepted().body(userList);
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
        System.out.println("User Id - " + userId);
//        List<User> userList = new ArrayList<>();
//        User user = new User(1,"ABC");
//        User user1 = new User(2,"ABC");
//        userList.add(user1);
//        userList.add(user);

        User user = userRepository.findById(userId).get();
        return ResponseEntity.accepted().body(user);
    }

    @PostMapping("/api/v1/users/")
    public ResponseEntity<User> createUser(@RequestBody User userId) {
        System.out.println("User Id - " + userId);
        List<User> userList = new ArrayList<>();
        User user = new User(1, "ABC");
        User user1 = new User(2, "ABC");
        userList.add(user1);
        userList.add(user);
        return ResponseEntity.accepted().body(user);
    }

    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId) {
        System.out.println("User Id - " + userId);
        List<User> userList = new ArrayList<>();
        User user = new User(1, "ABC");
        User user1 = new User(2, "ABC");
        userList.add(user1);
        userList.add(user);
        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) {
        System.out.println("User Id - " + userId);
        List<User> userList = new ArrayList<>();
        User user = new User(1, "ABC");
        User user1 = new User(2, "ABC");
        userList.add(user1);
        userList.add(user);
        return ResponseEntity.accepted().body(user);
    }
}
