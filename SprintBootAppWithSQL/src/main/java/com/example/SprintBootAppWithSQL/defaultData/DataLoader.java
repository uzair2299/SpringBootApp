package com.example.SprintBootAppWithSQL.defaultData;

import com.example.SprintBootAppWithSQL.entities.*;
import com.example.SprintBootAppWithSQL.services.*;
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
    DesignationService designationService;

    @Autowired
    MenuService menuService;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) {
        try {
            Long count = roleService.getRolesCount();
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

                List<Designation> designations = addDesignation();
                designationService.saveAllDesignations(designations);

                List<Department> departments = addDepartment();
                departmentService.saveAllDepartments(departments);

            }
        } catch (Exception e) {
            //throw e;
            logger.error(String.format("Exception - [%s]", e));
        }
    }

    private List<Designation> addDesignation() {
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
        return designations;
    }

    private List<Department> addDepartment() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Human Resources", "Responsible for managing employee recruitment, training, benefits, and other personnel-related issues."));
        departments.add(new Department("Finance", "Responsible for managing the organization's financial resources, including budgeting, accounting, and financial reporting."));
        departments.add(new Department("Marketing", "Responsible for promoting the organization's products or services, conducting market research, and managing the organization's branding and public image."));
        departments.add(new Department("Sales", "Responsible for generating revenue for the organization by selling its products or services to customers."));
        departments.add(new Department("Information Technology (IT)", "Responsible for managing the organization's computer systems, software, and other technology-related resources."));
        departments.add(new Department("Operations", "Responsible for overseeing the day-to-day activities of the organization, including production, logistics, and supply chain management."));
        departments.add(new Department("Legal", "Responsible for ensuring the organization operates in compliance with relevant laws and regulations, and for handling legal disputes and contracts."));
        departments.add(new Department("Customer Service", "Responsible for handling customer inquiries, complaints, and support issues."));
        departments.add(new Department("Research and Development", "Responsible for developing new products, services, and technologies to keep the organization competitive."));
        departments.add(new Department("Quality Assurance", "Responsible for ensuring that the organization's products or services meet the required quality standards."));
        return departments;
    }
}
