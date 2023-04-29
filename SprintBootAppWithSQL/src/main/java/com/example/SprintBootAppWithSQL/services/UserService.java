package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        userList = userRepository.findAll();
        return userList;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDto getUserByUserName(UserDto userDto) {
        User result = userRepository.findByUserNameAndPassword(userDto.getUserName());
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
        UserDto user = MapperUtil.mapObject(result, UserDto.class);
        return user;
    }
}