package com.example.SprintBootAppWithSQL.services.servicesImpl;

import com.example.SprintBootAppWithSQL.controller.LoginController;
import com.example.SprintBootAppWithSQL.dto.*;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.RoleRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    //    @Cacheable(value = "allDataCache")
    public List<RoleDto> getAllRoles() {
        logger.info(String.format("Entering method in RoleService.getAllRoles()"));
        List<RoleDto> roleDtoList;
        List<Role> roleList = roleRepository.findAll();
        roleDtoList = MapperUtil.mapList(roleList, RoleDto.class);
        logger.info(String.format("Leaving method in RoleService.getAllRoles()"));
        return roleDtoList;
    }

    public RoleDto getRoleById(Long roleId) {
        logger.info(String.format("Entering method in RoleService.getAllRoles()"));

        Role role = roleRepository.getRoleById(roleId);
        RoleDto roleDto = MapperUtil.mapObject(role, RoleDto.class);
        logger.info(String.format("Leaving method in RoleService.getAllRoles()"));
        return roleDto;
    }


    public List<Role> getAllRoles_() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

//    public RoleDto createRole(RoleDto roleDto) {
//        Role role = MapperUtil.mapObject(roleDto, Role.class);
//        Role savedRole = roleRepository.save(role);
//        RoleDto savedRoleDto = MapperUtil.mapObject(savedRole, RoleDto.class);
//        return savedRoleDto;
//    }

    @Transactional
    public void createRole(RoleDto roleDto) {
        roleRepository.createRole(roleDto.getRoleName(), roleDto.getCreatedAt(), roleDto.getUpdatedAt(), roleDto.isDeleted(), roleDto.getDescription());
    }

    @Transactional
    public void updateRole(RoleDto roleDto) {
        roleRepository.updateRole(roleDto.getId(),roleDto.getRoleName(), roleDto.getUpdatedAt(), roleDto.isDeleted(), roleDto.getDescription());
    }

    public List<Role> saveAll(List<Role> role) {
        return roleRepository.saveAll(role);
    }

    public Long getRolesCount() {
        return roleRepository.count();
    }

    public List<Role> getEntitiesByIds(List<Long> ids) {
        List<Role> roles = roleRepository.findByIdIn(ids);
        return roles;
    }

    public Map<Long, UserDto> getUserRolesWithUserDetails(long userId) {
        List<Object[]> userRoles = roleRepository.getUserRolesWithUserDetails(userId);
        List<RoleDto> roleDtoList = this.getAllRoles();

        Map<Long, UserDto> userDtoMap = new HashMap<>();
        for (Object[] object : userRoles) {
            UserDto userDto = new UserDto();
            userDto.setId((Long) object[0]);
            userDto.setUserName((String) object[1]);
            userDto.setFirstName((String) object[2]);
            userDto.setLastName((String) object[3]);
            userDto.setEmail((String) object[4]);
            userDtoMap.getOrDefault(userDto.getId(), userDto);

            if (object[5] != null) {
                for (RoleDto item : roleDtoList) {
                    if (item.getId().equals((Long) object[5])) {
                        item.setDeleted(true);
                    }
                }
            }

            userDto.setRoles(roleDtoList);
            userDtoMap.put(userDto.getId(), userDto);
        }
        return userDtoMap;
    }


    public List<Object[]> getRoleResourcePermission(RoleDto roleDto) {
        return roleRepository.getRoleResourcePermission(roleDto.getRoleIds(), roleDto.getEndPoint());
    }

    public List<Object[]> getRoleAssignResourcesPermission(RoleDto roleDto) {
        return roleRepository.getRoleAssignResourcesPermission(roleDto.getRoleIds());
    }


    public List<Role> getUserRoles(long userId) {
        return roleRepository.getUserRoles(userId);
    }

    @Transactional
    public void assignUserRoles(Long userId, List<UserRoleDto> userRoleDtos) {
        deleteUserRoles(userId);
        for (UserRoleDto item : userRoleDtos) {
            if (item.isMarked()) {
                roleRepository.assignUserRoles(userId, item.getRoleId());
            }
        }
    }


    @Transactional
    public void assignRolePermissions(Long roleId, List<ResourceDto> resourceDto) {
        roleRepository.deleteAssignRolePermissions(roleId);
        for (ResourceDto item : resourceDto) {
            for (PermissionDto permissionDto : item.getPermissions()) {
                if (permissionDto.isChecked()) {
                    roleRepository.assignRolePermissions(permissionDto.getResourcesPermissionsId(), roleId);
                }
            }
        }
    }

    public void deleteUserRoles(long userId) {
        roleRepository.deleteUserRoles(userId);
    }

}