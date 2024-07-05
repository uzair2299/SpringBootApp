package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.entities.UserRoleId;
import com.example.SprintBootAppWithSQL.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, UserRoleId> {

//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    User findByUserNameAndPassword(@Param("userName") String userName);
    //User findByUserName(@Param("userName") String userName);

    @Query(value = "SELECT r.id,r.role_Name FROM public.roles r\n" +
            "JOIN user_roles ur ON ur.role_id = r.id\n" +
            "WHERE ur.user_id =:userId",nativeQuery = true)
    List<Object[]>  findByUserId_(@Param("userId") long userId);
    
}

