package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.LoginDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.*;
import com.example.SprintBootAppWithSQL.repository.*;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesMenuService {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    RolesMenuRepository rolesMenuRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<User>();
//        userList = userRepository.findAll();
//        return userList;
//    }

    public void saveRolesMenu(Long menuId,Long roleId) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (menuOptional.isPresent() && roleOptional.isPresent()) {
            Menu menu = menuOptional.get();
            Role role = roleOptional.get();
            RolesMenu rolesMenu = new RolesMenu();
            rolesMenu.setMenu(menu);
            rolesMenu.setRole(role);
            rolesMenuRepository.save(rolesMenu);
        }else {
            // Handle case when user or role does not exist
            throw new IllegalArgumentException("User or Role not found");
        }
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
}