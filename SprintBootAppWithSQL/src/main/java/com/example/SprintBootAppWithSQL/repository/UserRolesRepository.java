package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long> {

//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    User findByUserNameAndPassword(@Param("userName") String userName);
    //User findByUserName(@Param("userName") String userName);
}

