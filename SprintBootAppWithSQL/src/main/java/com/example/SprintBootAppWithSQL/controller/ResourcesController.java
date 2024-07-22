package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.dto.ResourceDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.ResourcesRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.repository.UserRolesRepository;
import com.example.SprintBootAppWithSQL.services.UserService;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/resources")
@Slf4j
public class ResourcesController {
    @Autowired
    ResourcesRepository resourcesRepository;



    @GetMapping("/getAllResourcesWithPermissions")
    public ResponseEntity<List<ResourceDto>> getUsers() {
        try {
            log.info(String.format("Executing getUser request"));
            List<Object[]>  list =  resourcesRepository.getAll();

            Map<Long, ResourceDto> resourceMap = new HashMap<>();
            for(Object[] object : list){
                ResourceDto resourceDto = new ResourceDto();
                PermissionDto permissionDto = new PermissionDto();
                resourceDto.setResourceId(Long.valueOf((Integer)object[0]));
                resourceDto.setResourceEndpoint((String) object[1]);


                permissionDto.setId(Long.valueOf((Long) object[2]));
                permissionDto.setPermissionName((String) object[3]);
                ResourceDto resource = resourceMap.getOrDefault(resourceDto.getResourceId(), resourceDto);
                resource.getPermissions().add(permissionDto);
                resourceMap.put(resourceDto.getResourceId(), resource);
            }

            return ResponseEntity.ok().body(new ArrayList<>(resourceMap.values()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @GetMapping("/getUsers/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") long userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            Optional<User> user = userRepository.findById(userId);
//            if (user.isPresent()) {
//                System.out.println("User Id - " + userId);
//                System.out.println("User Id - " + userId);
//                System.out.println("User Id - " + userId);
//                System.out.println("User Id - " + userId);
//                System.out.println("User Id - " + userId);
//                System.out.println("User Id - " + userId);
//                //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
//                //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
//                //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
//                return ResponseEntity.ok().body(user.get());
//            }
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @GetMapping("/getUserRoles/{id}")
//    public ResponseEntity<List<Role>> getUserRoles(@PathVariable("id") long userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            List<Role> results = roleService.userRoles(userId);
//
//
////          List<Role> roleList =   results.stream().map(result -> {
////                Role role = new Role();
////                role.setId((Long) result[0]);
////                role.setRoleName((String) result[1]);
////                return role;
////            }).collect(Collectors.toList());
//
//                //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
//                //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
//                //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
//                return ResponseEntity.ok().body(results);
//
//               } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PostMapping("/api/v1/users/")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        try {
//            System.out.println("User - " + user);
//
//            List<Role> roles = roleService.getEntitiesByIds(user.getRolesId());
//            //user.setRoles(roles);
//            userRepository.save(user);
//            return new ResponseEntity<>(new User(), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PutMapping("/api/v1/users/info/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
//        UserDto userDto =  userService.updateUser(user,id);
//        return ResponseEntity.ok("User updated successfully.");
//    }
//
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
