package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Permission;
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

    @Query(value = "SELECT * FROM public.roles where id=:id",nativeQuery = true)
    Role getRoleById(@Param("id") Long id);

    @Query(value = "SELECT r.id AS r_role_id,\n" +
            "r.role_name,\n" +
            "rrp.role_id AS rrp_role_id,\n" +
            "rrp.resources_permissions_id AS rrp_resources_permissions_id,\n" +
            "rp.id AS rp_id,\n" +
            "rp.resource_id AS rp_resource_id,\n" +
            "rp.permission_id AS rp_permission_id,\n" +
            "p.permission_name,\n" +
            "p.id AS p_id,\n" +
            "re.id AS re_id,\n" +
            "re.resource_name,\n" +
            "re.method_type,\n" +
            "re.resource_endpoint\n" +
            "FROM roles r\n" +
            "JOIN roles_resources_permissions rrp ON r.id = rrp.role_id\n" +
            "JOIN resources_permissions rp ON rp.id = rrp.resources_permissions_id\n" +
            "JOIN public.permission  p ON p.id =rp.permission_id \n" +
            "JOIN resources re ON r.id = rp.resource_id\n" +
            "WHERE r.id =:roleId  AND re.resource_endpoint = :endPoint",nativeQuery = true)
    List<Object[]>  getRoleResourcePermission(@Param("roleId") long roleId,@Param("endPoint") String endPoint);


}

