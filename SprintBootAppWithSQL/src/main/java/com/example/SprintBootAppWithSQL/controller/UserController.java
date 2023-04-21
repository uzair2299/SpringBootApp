package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping("/api/v1/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            logger.info(String.format("Executing getUser request"));
            List<User> userList = new ArrayList<>();
            userList = userService.getAllUsers();
            if (userList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(userList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
        try {
            System.out.println("User Id - " + userId);
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return ResponseEntity.ok().body(user.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/api/v1/users/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            System.out.println("User - " + user);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId) {
        System.out.println("User Id - " + userId);
        List<User> userList = new ArrayList<>();
//        //User user = new User(UUID.randomUUID(), "ABC");
//        User user1 = new User(UUID.randomUUID(), "ABC");
//        userList.add(user1);
//        userList.add(user);
        return ResponseEntity.accepted().body(new User());
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) {
        try {
            System.out.println("User Id - " + userId);
            userRepository.deleteById(userId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
