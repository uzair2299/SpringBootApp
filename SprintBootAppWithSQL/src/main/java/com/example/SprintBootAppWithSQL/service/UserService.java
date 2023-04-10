package com.example.SprintBootAppWithSQL.service;

import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.UserRepository;
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
}