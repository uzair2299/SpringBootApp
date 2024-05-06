package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.services.PermissionService;
import com.example.SprintBootAppWithSQL.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class PermissionController {
    Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    PermissionService permissionService;


    @GetMapping("/api/v1/permission")
    public ResponseEntity<List<PermissionDto>> getPermissions() {
        try {

            logger.info(String.format("Executing getRoles request"));
            List<PermissionDto> permissionDtoList = permissionService.getAllPermissions();
            if (permissionDtoList.isEmpty()) {
                logger.info(String.format("Leaving getRoles request - roles list is empty"));
                return ResponseEntity.noContent().build();
            }
            logger.info(String.format("Leaving getRoles request - with roles list"));
            return ResponseEntity.ok().body(permissionDtoList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/api/v1/permission/{id}")
    public ResponseEntity<PermissionDto> getPermission(@PathVariable("id") int permissionId) {
        log.info("permission id receive - {}",permissionId);
        PermissionDto permissionDto =  permissionService.getPermissionById(permissionId);

       return ResponseEntity.ok().body(permissionDto);
    }

    @PostMapping("/api/v1/permission")
    public ResponseEntity<PermissionDto> createNewPermission(@RequestBody PermissionDto permissionDto) {
        try {
            logger.info(String.format("permissionDto - " + permissionDto));
            permissionDto.setCreatedAt(System.currentTimeMillis());
            PermissionDto permissionObj =  permissionService.save(permissionDto);
            return new ResponseEntity<>(permissionObj, HttpStatus.CREATED);
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
    @DeleteMapping("/api/v1/permission/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int permissionId) {
        logger.info("permission id receive - {}",permissionId);
        PermissionDto permissionDto =  permissionService.getPermissionById(permissionId);
        permissionService.updatePermissionIsDeleted(permissionDto);
       return ResponseEntity.ok().build();
    }
}
