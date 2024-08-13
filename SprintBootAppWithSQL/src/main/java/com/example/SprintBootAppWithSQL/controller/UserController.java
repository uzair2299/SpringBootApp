package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.PasswordResetDto;
import com.example.SprintBootAppWithSQL.dto.ResourceDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.entities.UserRoles;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.repository.UserRolesRepository;
import com.example.SprintBootAppWithSQL.services.servicesImpl.RoleService;
import com.example.SprintBootAppWithSQL.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        try {
            List<UserDto> userList = new ArrayList<>();
            userList = userService.getAllAppUsers();
            if (userList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(userList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                System.out.println("User Id - " + userId);
                //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
                //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
                //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
                return ResponseEntity.ok().body(user.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getUserRoles/{id}")
    public ResponseEntity<UserDto> getUserRoles(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            Map<Long, UserDto> userDtoMap = roleService.getUserRolesWithUserDetails(userId);
            UserDto result = userDtoMap.get(userId);

//          List<Role> roleList =   results.stream().map(result -> {
//                Role role = new Role();
//                role.setId((Long) result[0]);
//                role.setRoleName((String) result[1]);
//                return role;
//            }).collect(Collectors.toList());

            //In the context of a Spring Boot application, serialization to JSON typically occurs when the ResponseEntity is being prepared for return to the client. This process is managed by the Spring framework, which uses the Jackson library by default to convert the Java object into a JSON representation
            //The actual conversion to JSON is handled by Jackson. When Jackson serializes the User object, it will attempt to access all non-ignored fields. If any of these fields are lazily loaded collections or associations, accessing them will trigger the lazy loading query.
            //To prevent the lazy loading query for the userRoles field, use the @JsonIgnore annotation. This will ensure that Jackson ignores this field during serialization, thus preventing the lazy loading query from running.
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/api/v1/users/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            System.out.println("User - " + user);

            List<Role> roles = roleService.getEntitiesByIds(user.getRolesId());
            //user.setRoles(roles);
            userRepository.save(user);
            return new ResponseEntity<>(new User(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
    public ResponseEntity<String> resetUserPassword(@RequestBody PasswordResetDto passwordResetDto) {
        try {
            if(passwordResetDto.getNewPassword().equals(passwordResetDto.getConfirmPassword())){
                userService.resetUserPassword(passwordResetDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/api/v1/users/info/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        UserDto userDto = userService.updateUser(user, id);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        try {
            System.out.println("User Id - " + userId);
            userRepository.deleteById(userId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    //When you upload a file via a MultipartFile in a Spring Boot application, the file is temporarily stored in a location managed by the servlet container (e.g., Tomcat) before it is processed. The path you're seeing (C:\\Users\\user\\AppData\\Local\\Temp\\tomcat...) is where Tomcat stores temporary files.
    //Make sure that the temporary file is not being deleted or modified before your asynchronous task has a chance to process it. Since you are using CompletableFuture.runAsync, the temporary file might be removed by Tomcat's cleanup process before your task starts processing it.
    //read the content of the MultipartFile in the main thread and pass it to the asynchronous task to avoid accessing the temporary file directly. This approach prevents issues related to file deletion or path changes.
    @RequestMapping(value = "/bulkCreateUsersViaExcel", method = RequestMethod.POST)
    public ResponseEntity<String> bulkCreateUsersViaExcel(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        CompletableFuture.runAsync(() -> {
            List<UserDto> userDtoList = new ArrayList<>();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {

                log.info("thread name [{}]", Thread.currentThread().getName());
                XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(fileContent));
                XSSFSheet worksheet = workbook.getSheetAt(0);
                int rowNumber = 0;

                Iterator<Row> rowIterator = worksheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }

                    int cellNumber = 0;
                    UserDto userDto = new UserDto();

                    Iterator<Cell> cellIterator = row.iterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cellNumber) {
                            case 0:
                                userDto.setUserName(cell.getStringCellValue());
                                break;
                            case 1:
                                userDto.setFirstName(cell.getStringCellValue());
                                break;
                            case 2:
                                userDto.setLastName(cell.getStringCellValue());
                                break;
                            case 3:
                                userDto.setPassword(cell.getStringCellValue());
                                break;
                            case 4:
                                userDto.setEmail(cell.getStringCellValue());
                                break;
                            case 5:
                                userDto.setIsActive(cell.getBooleanCellValue());
                                break;
                            case 6:
                                userDto.setIsLocked(cell.getBooleanCellValue());
                                break;
                            case 7:
                                userDto.setDateJoined(cell.getDateCellValue().getTime());
                                break;

                        }

                        cellNumber++;
                    }
                    userDtoList.add(userDto);
                    log.info("size of user list [{}]", userDtoList.size());
                }
            } catch (Exception exception) {
                log.error("Error While Handling the Request " + exception.toString());
            }
            log.info("Data processing completed");
            userService.insertBulkUser(userDtoList);
        });
        return ResponseEntity.accepted().body("Request accepted and data processing started");
    }


}

