package com.example.SprintBootAppWithSQL.defaultData;

import com.example.SprintBootAppWithSQL.entities.Designation;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.services.MenuService;
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
    MenuService menuService;
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

                Menu parentMenu = new Menu(null, "Home", "/parent", 1);

                Menu childMenu1 = new Menu(null, "Child 1", "/parent/child1", 1);
                childMenu1.setParentMenu(parentMenu);
                Menu childMenu2 = new Menu(null, "Child 2", "/parent/child2", 1);

                List<Menu> menuList = new ArrayList<>();
                Menu aboutUs = new Menu(null, "About Us", "/aboutUs", 1);
                Menu contactUs = new Menu(null, "Contact Us", "/contactUs", 1);
                Menu faQs = new Menu(null, "FAQs", "/faQs", 1);
                Menu support = new Menu(null, "Support", "/support", 1);
                menuList.add(aboutUs);
                menuList.add(contactUs);
                menuList.add(faQs);
                menuList.add(support);
                menuService.saveAllMenuItem(menuList);

                childMenu2.setParentMenu(parentMenu);
                menuService.saveMenu(parentMenu);
                menuService.saveMenu(childMenu1);
                menuService.saveMenu(childMenu2);



            }
        } catch (Exception e) {
            //throw e;
            logger.error(String.format("Exception - [%s]", e));
        }
    }

    private void addDesignation(){
        List<Designation> designations = new ArrayList<>();
        designations.add(new Designation("CEO"));
        designations.add(new Designation("COO"));
        designations.add(new Designation("CFO"));
        designations.add(new Designation("CMO"));
        designations.add(new Designation("CIO"));
        designations.add(new Designation("CHRO"));
        designations.add(new Designation("CTO"));
        designations.add(new Designation("CCO"));
        designations.add(new Designation("CDO"));
        designations.add(new Designation("General Manager"));
        designations.add(new Designation("Managing Director"));
        designations.add(new Designation("Director"));
        designations.add(new Designation("Assistant Director"));
        designations.add(new Designation("Manager"));
        designations.add(new Designation("Team Lead"));
        designations.add(new Designation("Supervisor"));
        designations.add(new Designation("Executive Assistant"));
        designations.add(new Designation("Administrative Assistant"));
        designations.add(new Designation("Receptionist"));
        designations.add(new Designation("Accountant"));
        designations.add(new Designation("Financial Analyst"));
        designations.add(new Designation("Marketing Manager"));
        designations.add(new Designation("Sales Manager"));
        designations.add(new Designation("Business Analyst"));
        designations.add(new Designation("Data Analyst"));
        designations.add(new Designation("Software Developer"));
        designations.add(new Designation("Systems Administrator"));
        designations.add(new Designation("Network Engineer"));
        designations.add(new Designation("HR Manager"));
        designations.add(new Designation("Talent Acquisition Specialist"));
        designations.add(new Designation("Training Manager"));
        designations.add(new Designation("Customer Service Representative"));
        designations.add(new Designation("Operations Manager"));
        designations.add(new Designation("Supply Chain Manager"));
        designations.add(new Designation("Procurement Officer"));
        designations.add(new Designation("Quality Assurance Manager"));
        designations.add(new Designation("Compliance Officer"));
        designations.add(new Designation("Project Manager"));
        designations.add(new Designation("Product Manager"));
    }
}
