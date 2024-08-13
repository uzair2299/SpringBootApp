package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    User findByUserNameAndPassword(@Param("userName") String userName);
    User findByUserName(@Param("username") String username);


    @Query(value = "SELECT * FROM public.app_user",nativeQuery = true)
    List<User> getAllUsers();


}

