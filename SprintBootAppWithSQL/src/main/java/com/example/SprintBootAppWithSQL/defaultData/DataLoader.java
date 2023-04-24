package com.example.SprintBootAppWithSQL.defaultData;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) {
        try {
          Long count =  roleService.getRolesCount();
            if (count == 0) {
                Role role = new Role();
                role.setRoleName("Admin");
                role.setCreatedAt(new Date());
                role.setUpdatedAt(new Date());
                role.setDescription("Description");
                roleService.createRole(role);
            }
        } catch (Exception e) {
            //throw e;
            logger.error(String.format("Exception - [%s]", e));
        }
    }
}
