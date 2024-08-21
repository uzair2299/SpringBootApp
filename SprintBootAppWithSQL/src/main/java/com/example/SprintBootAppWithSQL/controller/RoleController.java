package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.*;
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

import java.util.*;

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



    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRole(@RequestBody RoleDto roleDto) {
        try {
            logger.info(String.format("roleDto - " + roleDto));
            roleDto.setUpdatedAt(System.currentTimeMillis());
            roleService.updateRole(roleDto);
            return ResponseEntity.ok().build();
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
    @RequestMapping(value = "/getRoleAssignResourcesPermission", method = RequestMethod.POST)
    public ResponseEntity<List<RoleAssignedResourcePermissionDTO>> getRoleAssignResourcesPermission(@RequestBody RoleDto roleDtos) {
        List<Object[]> o = roleService.getRoleAssignResourcesPermission(roleDtos);
        List<RoleResourcePermissionDTO> roleDto = new ArrayList<>();


//        rrp.resources_permissions_id,
//        rrp.role_id,
//        rp.permission_id,
//        rp.resource_id,
//        p.permission_name,
//        re.id as resource_id,
//        re.resource_name,
//        re.method_type,
//        re.resource_endpoint,
//        r.role_name


        Map<String, RoleAssignedResourcePermissionDTO> rolesAssignedResourcePermission = new HashMap<>();

        for (Object[] item : o) {


            //is used to add a new entry to the rolesAssignedResourcePermission map if it doesn't already contain a mapping for the specified key.
//            rolesAssignedResourcePermission.putIfAbsent(String.valueOf((Long) item[10]), new RoleAssignedResourcePermissionDTO());

//            RoleAssignedResourcePermissionDTO role = rolesAssignedResourcePermission.get(String.valueOf((Long) item[10]));
//            RoleDto r = new RoleDto();
//            r.setId((Long) item[10]);
//            r.setRoleName((String) item[9]);
//            role.setRoles(r);


            RoleAssignedResourcePermissionDTO rrp = rolesAssignedResourcePermission.computeIfAbsent(String.valueOf((Long) item[10]), id -> {
                RoleAssignedResourcePermissionDTO roleAssignedResourcePermissionDTO =  new RoleAssignedResourcePermissionDTO();
                RoleDto r = new RoleDto();
                r.setId((Long) item[10]);
                r.setRoleName((String) item[9]);
                roleAssignedResourcePermissionDTO.setRoles(r);
                return  roleAssignedResourcePermissionDTO;
            });


            //r.getResources().putIfAbsent(String.valueOf(Long.valueOf((Integer) item[5])),new ResourceDto());
            //ResourceDto resource = r.getResources().get(String.valueOf(Long.valueOf((Integer) item[5])));

            // Initialize resource if not already present
            ResourceDto resource = rrp.getRoles().getResources().stream()
                    .filter(re -> re.getResourceId() == Long.valueOf((Integer) item[3]))
                    .findFirst()
                    .orElseGet(() -> {
                        ResourceDto resourceDto = new ResourceDto();
                        resourceDto.setResourceName((String) item[6]);
                        resourceDto.setResourceId(Long.valueOf((Integer) item[3]));
                        resourceDto.setMethodType((String) item[7]);
                        resourceDto.setResourceEndpoint((String) item[8]);
                        rrp.getRoles().getResources().add(resourceDto);
                        return resourceDto;
                    });




            PermissionDto p = new PermissionDto();
            p.setId(Long.valueOf((Integer) item[2]));
            p.setPermissionName((String) item[4]);
            resource.getPermissions().add(p);

//
//
//
//            RoleResourcePermissionDTO permissionDTO = new RoleResourcePermissionDTO();
//            permissionDTO.setResourcesPermissionsId((Long) item[0]);
//            permissionDTO.setRRoleId((Long) item[1]);
//            permissionDTO.setPId((Long) item[2]);
//            permissionDTO.setReId(Long.valueOf((Integer) item[3]));
//            permissionDTO.setPermissionName((String) item[4]);
//            permissionDTO.setReId(Long.valueOf((Integer) item[5]));
//            permissionDTO.setResourceName((String) item[6]);
//            permissionDTO.setMethodType((String) item[7]);
//            permissionDTO.setResourceEndpoint((String) item[8]);
//            permissionDTO.setRoleName((String) item[9]);
//            permissionDTO.setRRoleId((Long) item[10]);
//            roleDto.add(permissionDTO);

        }

        List<RoleAssignedResourcePermissionDTO> roleDtoList = new ArrayList<>(rolesAssignedResourcePermission.values());
        return ResponseEntity.ok().body(roleDtoList);
    }


    @RequestMapping(value = "/assignRolesPermission/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> assignRolesPermission(@PathVariable("id") long roleId, @RequestBody List<ResourceDto> resourceDtos) {
        try {

            roleService.assignRolePermissions(roleId, resourceDtos);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @RequestMapping(value = "/assignUserRoles/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> assignUserRoles(@PathVariable("id") long userId, @RequestBody List<Object> o) {
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
