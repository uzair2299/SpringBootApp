package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.dto.ResourceDto;
import com.example.SprintBootAppWithSQL.dto.ResourcePermissionDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Resource;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.ResourcesRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.repository.UserRolesRepository;
import com.example.SprintBootAppWithSQL.services.PermissionService;
import com.example.SprintBootAppWithSQL.services.ResourcesService;
import com.example.SprintBootAppWithSQL.services.UserService;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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

    @Autowired
    PermissionService permissionService;

    @Autowired
    ResourcesService resourcesService;


    @GetMapping("/getAllResourcesWithPermissions")
    public ResponseEntity<List<ResourceDto>> getAllResourcesWithPermissions() {
        try {
            log.info(String.format("Executing getUser request"));
            List<Object[]> list = resourcesRepository.getAllResourcesWithPermissions();

            Map<Long, ResourceDto> resourceMap = new HashMap<>();
            for (Object[] object : list) {
                ResourceDto resourceDto = new ResourceDto();
                PermissionDto permissionDto = new PermissionDto();
                resourceDto.setResourceId(Long.valueOf((Integer) object[0]));
                resourceDto.setResourceEndpoint((String) object[1]);
                resourceDto.setVersion((String) object[2]);
                resourceDto.setMethodType((String) object[3]);

                ResourceDto resource = resourceMap.getOrDefault(resourceDto.getResourceId(), resourceDto);

                if (object.length > 2 && object[4] != null) {
                    permissionDto.setId(Long.valueOf(String.valueOf(object[4])));
                }

                if (object.length > 3 && object[5] != null) {
                    permissionDto.setPermissionName(String.valueOf(object[5]));
                    permissionDto.setResourcesPermissionsId((Long)object[6]);
                    resource.getPermissions().add(permissionDto);
                }


                resourceMap.put(resourceDto.getResourceId(), resource);
            }

            return ResponseEntity.ok().body(new ArrayList<>(resourceMap.values()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/getAllResources")
    public ResponseEntity<List<ResourceDto>> getAllResources() {
        try {
            log.info(String.format("Executing getUser request"));
            List<ResourceDto> list = resourcesService.getAll();

            return ResponseEntity.ok().body(list);
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

    @RequestMapping(value = "/getResourceById/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResourceDto> getResourceById(@PathVariable("id") long resourceId) {
        try {
            System.out.println("resourceId- " + resourceId);
            Map<Long, ResourceDto> resource = resourcesService.getResourceByIdWithPermissions(resourceId);
            ResourceDto result = resource.get(resourceId);
            //List<PermissionDto> permissionDtoList = permissionService.getAllActive();
            //results.setPermissions(permissionDtoList);

//          List<Role> roleList =   results.stream().map(result -> {
//                Role role = new Role();
//                role.setId((Long) result[0]);
//                role.setRoleName((String) result[1]);
//                return role;
//            }).collect(Collectors.toList());

            //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
            //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
            //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            log.error("going to log exception" + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/assignResourcePermission/{id}", method = RequestMethod.POST)
    public ResponseEntity<User> assignResourcePermission(@PathVariable("id") long resourceId, @RequestBody List<Object> o) {
        try {
            String json = MapperUtil.convertListToJsonString(o);
            List<ResourcePermissionDto> resourcePermissionDto = MapperUtil.convertJsonStringToList(json, new TypeReference<List<ResourcePermissionDto>>() {
            });
            resourcesService.assignResourcePermission(resourceId, resourcePermissionDto);
            return new ResponseEntity<>(new User(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
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


    @RequestMapping(value = "/createResource", method = RequestMethod.POST)
    ResponseEntity<String> createResource(@RequestBody ResourceDto resourceDto){

        resourcesService.createResource(resourceDto);
        return ResponseEntity.ok().build();
    }
}
