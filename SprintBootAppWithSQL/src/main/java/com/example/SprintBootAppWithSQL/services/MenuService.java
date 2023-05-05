package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.repository.MenuRepository;
import com.example.SprintBootAppWithSQL.repository.RoleRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getAllRoles() {
        List<Menu> menuList = menuRepository.findAll();
        return menuList;
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> saveAllMenuItem(List<Menu> menuList) {
        return menuRepository.saveAll(menuList);
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
//    public List<Role> saveAll(List<Role> role) {
//        return roleRepository.saveAll(role);
//    }
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