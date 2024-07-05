package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.entities.UserRoles;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.repository.UserRolesRepository;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import com.example.SprintBootAppWithSQL.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


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

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
                //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
                //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
                return ResponseEntity.ok().body(user.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getUserRoles/{id}")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            List<Object[]> results = userRolesRepository.findByUserId_(userId);


          List<Role> roleList =   results.stream().map(result -> {
                Role role = new Role();
                role.setId((Long) result[0]);
                role.setRoleName((String) result[1]);
                return role;
            }).collect(Collectors.toList());
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
                //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
                //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
                return ResponseEntity.ok().body(roleList);

               } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/api/v1/users/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            System.out.println("User - " + user);

            List<Role> roles = roleService.getEntitiesByIds(user.getRolesId());
            //user.setRoles(roles);
            userRepository.save(user);
            return new ResponseEntity<>(new User(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/api/v1/users/info/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        UserDto userDto =  userService.updateUser(user,id);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            userRepository.deleteById(userId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
