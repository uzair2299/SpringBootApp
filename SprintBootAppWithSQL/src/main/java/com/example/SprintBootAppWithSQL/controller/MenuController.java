package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.MenuDto;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.services.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class MenuController {
    @Autowired
    MenuService menuService;


    @GetMapping("/api/v1/userMenu")
    public ResponseEntity<List<MenuDto>> getUserMenu() {
        try {

            log.info(String.format("Executing getRoles request"));
            List<MenuDto> rolesList;
            rolesList = menuService.getUserMenu();
            if (rolesList.isEmpty()) {
                log.info(String.format("Leaving getRoles request - roles list is empty"));
                return ResponseEntity.noContent().build();
            }
            log.info(String.format("Leaving getRoles request - with roles list"));
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
//    @PostMapping("/api/v1/roles")
//    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
//        try {
//            log.info(String.format("roleDto - " + roleDto));
//            roleDto.setCreatedAt(new Date());
//            roleDto.setUpdatedAt(new Date());
//            RoleDto role = menuService.createRole(roleDto);
//            return new ResponseEntity<>(role, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

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
