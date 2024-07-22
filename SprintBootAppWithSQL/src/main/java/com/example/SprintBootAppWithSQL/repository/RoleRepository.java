package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.spi.LocaleNameProvider;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByIdIn(List<Long> ids);


    //org.postgresql.util.PSQLException: The column name description was not found in this ResultSet.
    //    at org.postgresql.jdbc.PgResultSet.findColumn(PgResultSet.java:2958)
    //    at com.zaxxer.hikari.pool.HikariProxyResultSet.findColumn(HikariProxyResultSet.java)
    @Query(value = "SELECT r.id,r.role_Name,r.created_at,r.description,r.updated_at FROM public.roles r\n" +
            "JOIN user_roles ur ON ur.role_id = r.id\n" +
            "WHERE ur.user_id =:userId",nativeQuery = true)
    List<Role>  findByUserId_(@Param("userId") long userId);
}

