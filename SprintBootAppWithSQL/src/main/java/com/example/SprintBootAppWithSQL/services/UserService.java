package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        userList = userRepository.findAll();
        return userList;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDto getUserByUserName(UserDto userDto){
       User result =  userRepository.findByUserName(userDto.getUserName());
       UserDto user = MapperUtil.mapObject(result,UserDto.class);
       return  user;
    }
}