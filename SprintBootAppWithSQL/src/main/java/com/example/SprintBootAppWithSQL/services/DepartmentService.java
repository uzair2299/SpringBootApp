package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.entities.Department;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.repository.DepartmentRepository;
import com.example.SprintBootAppWithSQL.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

//    public List<Department> getAllRoles() {
//        List<Menu> menuList = departmentRepository.findAll();
//        return menuList;
//    }

//    public Menu saveMenu(Menu menu) {
//        return menuRepository.save(menu);
//    }

    public List<Department> saveAllDepartments(List<Department> departments) {
        return departmentRepository.saveAll(departments);
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