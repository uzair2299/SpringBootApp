package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    User findByUserNameAndPassword(@Param("userName") String userName);

    User findByUserName(@Param("userName") String userName);
}

