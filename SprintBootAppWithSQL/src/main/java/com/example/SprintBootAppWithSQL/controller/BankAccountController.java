package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.BankAccountDto;
import com.example.SprintBootAppWithSQL.services.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
    Logger logger = LoggerFactory.getLogger(BankAccountController.class);
    @Autowired
    BankAccountService bankAccountService;


//    @GetMapping("/api/v1/roles")
//    public ResponseEntity<List<RoleDto>> getRoles() {
//        try {
//            logger.info(String.format("Executing getUser request"));
//            List<RoleDto> rolesList;
//            rolesList = roleService.getAllRoles();
//            if (rolesList.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok().body(rolesList);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    @GetMapping("/api/v1/role/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            Optional<User> user = userRepository.findById(userId);
//            if (user.isPresent()) {
//                return ResponseEntity.ok().body(user.get());
//            }
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
    @PostMapping("/api/v1/bankAccount")
    public ResponseEntity<BankAccountDto> saveEmployeeBankAccount(@RequestBody BankAccountDto bankAccountDto) {
        try {
            logger.info(String.format("bank account - " + bankAccountDto));
            bankAccountService.saveBankAccountDetails(bankAccountDto);
            return new ResponseEntity<>(new BankAccountDto(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PutMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") int userId) {
//        System.out.println("User Id - " + userId);
//        List<User> userList = new ArrayList<>();
////        //User user = new User(UUID.randomUUID(), "ABC");
////        User user1 = new User(UUID.randomUUID(), "ABC");
////        userList.add(user1);
////        userList.add(user);
//        return ResponseEntity.accepted().body(new User());
//    }
//
//    @DeleteMapping("/api/v1/users/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) {
//        try {
//            System.out.println("User Id - " + userId);
//            userRepository.deleteById(userId);
//            return ResponseEntity.accepted().build();
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
