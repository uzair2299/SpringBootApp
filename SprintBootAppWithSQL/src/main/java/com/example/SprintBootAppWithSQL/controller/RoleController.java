package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RoleController {
    Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;


    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<RoleDto>> getRoles() {
        try {

            logger.info(String.format("Executing getRoles request"));
            List<RoleDto> rolesList;
            rolesList = roleService.getAllRoles();
            if (rolesList.isEmpty()) {
                logger.info(String.format("Leaving getRoles request - roles list is empty"));
                return ResponseEntity.noContent().build();
            }
            logger.info(String.format("Leaving getRoles request - with roles list"));
            return ResponseEntity.ok().body(rolesList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @GetMapping("/api/v1/role/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            Optional<User> user = userRepository.findById(userId);
//            if (user.isPresent()) {
//                return ResponseEntity.ok().body(user.get());
//            }
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
    @PostMapping("/api/v1/roles")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        try {
            logger.info(String.format("roleDto - " + roleDto));
            roleDto.setCreatedAt(new Date());
            roleDto.setUpdatedAt(new Date());
            RoleDto role = roleService.createRole(roleDto);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PutMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") int userId) {
//        System.out.println("User Id - " + userId);
//        List<User> userList = new ArrayList<>();
////        //User user = new User(UUID.randomUUID(), "ABC");
////        User user1 = new User(UUID.randomUUID(), "ABC");
////        userList.add(user1);
////        userList.add(user);
//        return ResponseEntity.accepted().body(new User());
//    }
//
//    @DeleteMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            userRepository.deleteById(userId);
//            return ResponseEntity.accepted().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
