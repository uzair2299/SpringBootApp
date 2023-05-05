package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.RoleRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<RoleDto> getAllRoles() {
        List<RoleDto> roleDtoList;
        List<Role> roleList = roleRepository.findAll();
        roleDtoList = MapperUtil.mapList(roleList,RoleDto.class);
        return roleDtoList;
    }


    public List<Role> getAllRoles_() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role =  MapperUtil.mapObject(roleDto,Role.class);
        Role savedRole = roleRepository.save(role);
        RoleDto savedRoleDto = MapperUtil.mapObject(savedRole, RoleDto.class);
        return savedRoleDto;
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

}