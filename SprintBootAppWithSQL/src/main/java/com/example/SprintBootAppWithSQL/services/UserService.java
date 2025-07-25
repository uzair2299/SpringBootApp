package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.*;
import com.example.SprintBootAppWithSQL.entities.BankAccount;
import com.example.SprintBootAppWithSQL.entities.Employee;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        userList = userRepository.findAll();
        return userList;
    }

    public List<UserDto> getAllAppUsers() {
        List<User> userList = userRepository.getAllUsers();
        List<UserDto> userDtoList = MapperUtil.mapList(userList, UserDto.class);
        return userDtoList;

    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public LoginDto getUserByUserName(LoginDto userDto) {
        User result = userRepository.findByUserName(userDto.getUserName());
        if (result == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (result != null && result.getIsActive().equals(false)) {
            throw new DisabledException("user account is disabled");
        }
        if (result != null && !result.getIsLocked().equals(false)) {
            throw new LockedException(" user account is locked");
        }
        if (!passwordEncoder.matches(userDto.getPassword(), result.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        LoginDto user = MapperUtil.mapObject(result, LoginDto.class);
        return user;
    }

    public UserDto updateUser(UserDto userDto, long id) {

        User result = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        result.setPassword(passwordEncoder.encode(userDto.getPassword()));
        result.setEmail(userDto.getEmail());
        result.setUserName(userDto.getUserName());
        result.setFirstName(userDto.getFirstName());
        result.setLastName(userDto.getLastName());
        UserDto user = MapperUtil.mapObject(result, UserDto.class);
        userRepository.saveAndFlush(result);
        return user;
    }

    @Transactional
    public void insertBulkUser(List<UserDto> userDtoList) {

        for (UserDto item : userDtoList) {
            // userRepository.insertBulkUser(item.getUserName(),item.getFirstName(),item.getLastName());

        }
    }


    @Transactional
    public void resetUserPassword(PasswordResetDto passwordResetDto){
        userRepository.resetUserPassword(passwordResetDto.getId(),passwordEncoder.encode(passwordResetDto.getConfirmPassword()));
    }
}

