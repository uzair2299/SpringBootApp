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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("ABC");
        user.setPassword("XYZ");
        return UserDetailsImpl.build(user);
    }
}
