package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.EmployeeDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.EmployeeRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.services.EmployeeService;
import com.example.SprintBootAppWithSQL.services.RoleService;
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
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


//    @GetMapping("/api/v1/users")
//    public ResponseEntity<List<User>> getUsers() {
//        try {
//            logger.info(String.format("Executing getUser request"));
//            List<User> userList = new ArrayList<>();
//            userList = userService.getAllUsers();
//            if (userList.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok().body(userList);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    @GetMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") long userId) {
//        try {
//
//            Optional<User> user = userRepository.findById(userId);
//            if (user.isPresent()) {
//                return ResponseEntity.ok().body(user.get());
//            }
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    @PostMapping("/api/v1/employee/")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            logger.info(String.format("employee [%s]",employeeDto));
            EmployeeDto result =  employeeService.saveEmployee(employeeDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PutMapping("/api/v1/users/info/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
//        UserDto userDto =  userService.updateUser(user,id);
//        return ResponseEntity.ok("User updated successfully.");
//    }

//    @DeleteMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            userRepository.deleteById(userId);
//            return ResponseEntity.accepted().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
