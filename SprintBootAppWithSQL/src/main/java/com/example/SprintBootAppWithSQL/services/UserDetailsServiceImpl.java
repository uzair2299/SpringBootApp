package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1);
        user.setEmail("ABC");
        user.setPassword("XYZ");
        user.setUserName("testing");
        return UserDetailsImpl.build(user);
    }
}
