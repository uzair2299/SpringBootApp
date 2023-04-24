package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.RoleRepository;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<Role>();
        roleList = roleRepository.findAll();
        return roleList;
    }

    public Role createRole(Role role){
       return roleRepository.save(role);
    }
    public Long getRolesCount(){
        return roleRepository.count();
    }
}