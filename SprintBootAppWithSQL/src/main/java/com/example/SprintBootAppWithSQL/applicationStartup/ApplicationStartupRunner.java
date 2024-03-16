package com.example.SprintBootAppWithSQL.applicationStartup;

import com.example.SprintBootAppWithSQL.entities.*;
import com.example.SprintBootAppWithSQL.repository.PermissionRepository;
import com.example.SprintBootAppWithSQL.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Autowired
    private ApplicationContext applicationContext;

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
    DocumentTypeService documentTypeService;
    @Autowired
    WorkTypeService workTypeService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PermissionService permissionService;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ApplicationStartupRunner(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


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
                user.setPassword(passwordEncoder.encode("asdfghA1"));
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

                List<DocumentType> documentTypes = addDocumentType();
                documentTypeService.saveAllDocumentType(documentTypes);

                List<WorkType> workTypes = addWorkType();
                workTypeService.saveAllWorkTypes(workTypes);

                permissionService.saveAll(addPermission());
                // Clear Redis data
                redisTemplate.getConnectionFactory().getConnection().flushDb();

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

    private List<DocumentType> addDocumentType() {
        List<DocumentType> documentTypes = new ArrayList<>();
        documentTypes.add(new DocumentType("Offer letters", "These are documents that the company issues to new employees, outlining the terms and conditions of their employment, including job title, salary, benefits, and start date."));
        documentTypes.add(new DocumentType("Employment contracts", "These are legal agreements between the company and its employees that formalize the terms and conditions of their employment, including job responsibilities, compensation, benefits, and termination provisions."));
        documentTypes.add(new DocumentType("Disciplinary actions", "These are documents that the company may issue to employees who have violated company policies or engaged in misconduct. Disciplinary actions may include verbal or written warnings, suspension, or termination."));
        documentTypes.add(new DocumentType("Payroll records", "These are documents that track the compensation and benefits paid to employees, including salary, overtime, bonuses, and deductions."));
        documentTypes.add(new DocumentType("Resignation letters", "These are documents that employees may submit to inform the company of their intention to resign from their position. Resignation letters typically include the employee's name, job title, and date of resignation."));
        documentTypes.add(new DocumentType("Policies and procedures", "These are documents that outline the company's rules, guidelines, and processes for various aspects of its operations, such as employee conduct, safety, and data privacy. Policies and procedures may be used to ensure compliance with legal and regulatory requirements, to maintain consistency across the organization, and to promote best practices."));
        documentTypes.add(new DocumentType("Resumes or CVs", "hhh"));
        return documentTypes;
    }

    private List<WorkType> addWorkType() {
        List<WorkType> workTypes = new ArrayList<>();
        workTypes.add(new WorkType("Full Time"));
        workTypes.add(new WorkType("Part Time"));
        workTypes.add(new WorkType("Freelance"));
        workTypes.add(new WorkType("Remote"));
        workTypes.add(new WorkType("Intern"));
        workTypes.add(new WorkType("Consultant"));
        workTypes.add(new WorkType("Contract"));
        workTypes.add(new WorkType("Temporary"));
        workTypes.add(new WorkType("Volunteer"));
        return workTypes;
    }

    private List<Permission> addPermission() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission("VIEW", "Permission to view/read data or content."));
        permissions.add(new Permission("LIST", "Permission to list multiple items or records."));
        permissions.add(new Permission("SEARCH", "Permission to search for specific items or records."));
        permissions.add(new Permission("EXPORT", "Permission to export data or content."));
        permissions.add(new Permission("CREATE", "Permission to create new items or records."));
        permissions.add(new Permission("EDIT", "Permission to modify existing items or records."));
        permissions.add(new Permission("UPDATE", "Permission to update specific fields or properties of items or records."));
        permissions.add(new Permission("MODIFY", "General permission to make changes (can include both edit and update)."));
        permissions.add(new Permission("DELETE", "Permission to delete items or records."));
        permissions.add(new Permission("REMOVE", "Permission to remove items or records (may imply a soft delete or archive action)."));
        permissions.add(new Permission("ADMINISTER", "Permission to perform administrative tasks or manage system settings."));
        permissions.add(new Permission("MANAGE", "General permission to manage resources, users, or configurations."));
        permissions.add(new Permission("ACCESS", "Permission to access a specific feature, module, or area of the application."));
        permissions.add(new Permission("AUTHORIZE", "Permission to authorize or grant access to other users."));
        permissions.add(new Permission("EXECUTE", "Permission to execute or run specific operations or commands."));
        permissions.add(new Permission("PERFORM", "General permission to perform actions (can include various operations)."));
        permissions.add(new Permission("CUSTOM", "Custom-defined permissions tailored to specific application requirements."));
        return permissions;
    }
}
