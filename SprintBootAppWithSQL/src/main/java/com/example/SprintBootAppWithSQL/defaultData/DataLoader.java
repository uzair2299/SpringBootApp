package com.example.SprintBootAppWithSQL.defaultData;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.services.RoleService;
import com.example.SprintBootAppWithSQL.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        try {
          Long count =  roleService.getRolesCount();
            if (count == 0) {
                List<Role> roleList = new ArrayList<>();
                Role role = new Role();
                role.setRoleName("Admin");
                role.setCreatedAt(new Date());
                role.setUpdatedAt(new Date());
                role.setDescription("Description");

                Role superAdmin = new Role();
                superAdmin.setRoleName("Super Admin");
                superAdmin.setCreatedAt(new Date());
                superAdmin.setUpdatedAt(new Date());
                superAdmin.setDescription("This role has full control over all aspects of the application and can manage other admins");

                Role userAdministrator = new Role();
                userAdministrator.setRoleName("User Administrator");
                userAdministrator.setCreatedAt(new Date());
                userAdministrator.setUpdatedAt(new Date());
                userAdministrator.setDescription("This role is responsible for managing user accounts, including creating and deleting accounts, resetting passwords, and managing user permissions");

                roleList.add(role);
                roleList.add(superAdmin);
                roleList.add(userAdministrator);
                roleService.saveAll(roleList);


                List<Role> roles = roleService.getAllRoles_();

                User user = new User();
                user.setPassword(passwordEncoder.encode("testing"));
                user.setUserName("testing");
                user.setEmail("testing@gmail.com");
                user.setBio("testing");
                user.setFirstName("testing");
                user.setLastName("testing");
                user.setIsActive(true);
                user.setIsLocked(false);
                user.setRoles(roles);
                userService.saveUser(user);



            }
        } catch (Exception e) {
            //throw e;
            logger.error(String.format("Exception - [%s]", e));
        }
    }
}
