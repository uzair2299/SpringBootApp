package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.controller.LoginController;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.EntityManager;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Cacheable(value = "allDataCache")
    public List<RoleDto> getAllRoles() {
        logger.info(String.format("Entering method in RoleService.getAllRoles()"));
        //StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("GetUserById");
        //query.execute();
        //List<Role> roles = query.getResultList();

        List<RoleDto> roleDtoList;
        List<Role> roleList = roleRepository.findAll();
        roleDtoList = MapperUtil.mapList(roleList,RoleDto.class);
        logger.info(String.format("Leaving method in RoleService.getAllRoles()"));
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