package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.ResourcePermissionDto;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.dto.RoleResourcePermissionDTO;
import com.example.SprintBootAppWithSQL.dto.UserRoleDto;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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
@RequestMapping("/api/v1/roles")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;


    @GetMapping("/getAllRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
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

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long id) {
        try {
            System.out.println("User Id - " + id);
            RoleDto roleDto = roleService.getRoleById(id);
            return ResponseEntity.ok().body(roleDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        try {
            logger.info(String.format("roleDto - " + roleDto));
            roleDto.setCreatedAt(System.currentTimeMillis());
            roleDto.setUpdatedAt(System.currentTimeMillis());
            roleService.createRole(roleDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }



    //@GetMapping("/getRoleResourcePermission")
    @RequestMapping(value = "/getRoleResourcePermission", method = RequestMethod.POST)
    public ResponseEntity<List<RoleResourcePermissionDTO>>  getRoleResourcePermission(@RequestBody RoleDto roleDtos){
        List<Object[]> o =  roleService.getRoleResourcePermission(roleDtos);
        List<RoleResourcePermissionDTO> roleDto = new ArrayList<>();

        for(Object[] item :o){
            RoleResourcePermissionDTO permissionDTO = new RoleResourcePermissionDTO();
            permissionDTO.setRRoleId((Long) item[0]);
            permissionDTO.setRoleName((String) item[1]);
            permissionDTO.setRrpRoleId(Long.valueOf((Integer) item[2]));
            permissionDTO.setRrpResourcesPermissionsId(Long.valueOf((Integer) item[3]));
            permissionDTO.setRpId((Long) item[4]);
            permissionDTO.setRpResourceId(Long.valueOf((Integer) item[5]));

            permissionDTO.setRpPermissionId(Long.valueOf((Integer) item[6]));
            permissionDTO.setPermissionName((String) item[7]);
            permissionDTO.setPId((Long) item[8]);
            permissionDTO.setReId(Long.valueOf((Integer) item[9]));
            permissionDTO.setResourceName((String) item[10]);
            permissionDTO.setMethodType((String) item[11]);
            permissionDTO.setResourceEndpoint((String) item[12]);
            roleDto.add(permissionDTO);

        }

        return ResponseEntity.ok().body(roleDto);
    }


    @RequestMapping(value = "/assignUserRoles/{id}", method = RequestMethod.POST)
    public ResponseEntity<?>  assignUserRoles(@PathVariable("id") long userId, @RequestBody List<Object> o){
        try {
            String json = MapperUtil.convertListToJsonString(o);
            List<UserRoleDto> userRoleDtos = MapperUtil.convertJsonStringToList(json, new TypeReference<List<UserRoleDto>>() {
            });
            roleService.assignUserRoles(userId, userRoleDtos);
            return ResponseEntity.ok().build();
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
