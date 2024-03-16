package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.repository.PermissionRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private EntityManager entityManager;

//    @Cacheable(value = "allDataCache")
    public List<PermissionDto> getAllPermissions() {
        log.info(String.format("Entering method in PermissionService.getAllPermissions()"));

        List<PermissionDto> roleDtoList;
        List<Permission> roleList = permissionRepository.findAll();
        roleDtoList = MapperUtil.mapList(roleList,PermissionDto.class);
        log.info(String.format("Leaving method in PermissionService.getAllPermissions()"));
        return roleDtoList;
    }


//
//    public List<Role> getAllRoles_() {
//        List<Role> roleList = roleRepository.findAll();
//        return roleList;
//    }
//
//    public RoleDto createRole(RoleDto roleDto) {
//        Role role =  MapperUtil.mapObject(roleDto,Role.class);
//        Role savedRole = roleRepository.save(role);
//        RoleDto savedRoleDto = MapperUtil.mapObject(savedRole, RoleDto.class);
//        return savedRoleDto;
//    }
//
    public List<Permission> saveAll(List<Permission> role) {
        return permissionRepository.saveAll(role);
    }
//
//    public Long getRolesCount() {
//        return roleRepository.count();
//    }
//
//    public List<Role> getEntitiesByIds(List<Long> ids) {
//        List<Role> roles = roleRepository.findByIdIn(ids);
//        return roles;
//    }

}