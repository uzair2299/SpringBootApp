package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.spi.LocaleNameProvider;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByOrderByIdAsc();


    List<Role> findByIdIn(List<Long> ids);


    //org.postgresql.util.PSQLException: The column name description was not found in this ResultSet.
    //    at org.postgresql.jdbc.PgResultSet.findColumn(PgResultSet.java:2958)
    //    at com.zaxxer.hikari.pool.HikariProxyResultSet.findColumn(HikariProxyResultSet.java)
    @Query(value = "SELECT * FROM public.roles WHERE (:searchValue IS NULL OR role_name ILIKE %:searchValue%)", nativeQuery = true)
    List<Role>  findAllRolesOrderByIdAsc(@Param("searchValue") String searchValue);

    @Query(value = "SELECT r.id,r.role_Name,r.created_at,r.description,r.updated_at FROM public.roles r\n" +
            "JOIN user_roles ur ON ur.role_id = r.id\n" +
            "WHERE ur.user_id =:userId",nativeQuery = true)
    List<Role>  getUserRoles(@Param("userId") long userId);

    @Query(value = "SELECT u.id as user_id, u.user_name, u.first_name,u.last_name,u.email, r.id AS role_id,r.role_name FROM app_user AS u\n" +
            "LEFT JOIN user_roles ur ON ur.user_id=u.id\n" +
            "LEFT JOIN roles r ON r.id =ur.role_id\n" +
            "WHERE u.id =:userId",nativeQuery = true)
    List<Object[]>  getUserRolesWithUserDetails(@Param("userId") long userId);

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
            "WHERE r.id IN :roleIds  AND re.resource_endpoint = :endPoint",nativeQuery = true)
    List<Object[]>  getRoleResourcePermission(@Param("roleIds") List<Long> roleIds,@Param("endPoint") String endPoint);


    @Query(value = "SELECT rrp.resources_permissions_id,\n" +
            "rrp.role_id,\n" +
            "rp.permission_id,\n" +
            "rp.resource_id,\n" +
            "p.permission_name,\n" +
            "re.id as resource_id,\n" +
            "re.resource_name,\n" +
            "re.method_type,\n" +
            "re.resource_endpoint,\n" +
            "r.role_name,\n" +
            "r.id as role_id\n" +
            "FROM public.roles_resources_permissions as rrp\n" +
            "JOIN resources_permissions rp ON rrp.resources_permissions_id = rp.id\n" +
            "JOIN public.permission p ON rp.permission_id = p.id \n" +
            "JOIN public.resources re ON rp.resource_id = re.id\n" +
            "JOIN public.roles r ON rrp.role_id = r.id\n" +
            "WHERE rrp.role_id IN:roleIds",nativeQuery = true)
    List<Object[]>  getRoleAssignResourcesPermission(@Param("roleIds") List<Long> roleIds);


    //The @Transactional annotation is used to define the scope of a single database transaction. When applied to a method or a class, it specifies that the method or all methods in the class are transactional. This means that the methods will be executed within a transaction context, and they will either all succeed or all fail (rollback) as a unit.
    //The @Modifying annotation is used with a query method to indicate that the query is not a SELECT query and will modify the database (i.e., it's an INSERT, UPDATE, or DELETE operation). This annotation is necessary because Spring Data JPA's default behavior expects methods with @Query to be read-only unless explicitly stated otherwise.
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles u_r WHERE u_r.user_id = :user_id",nativeQuery = true)
    void deleteUserRoles(@Param("user_id")Long user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles(user_id,role_id) VALUES(:user_id,:role_id)",nativeQuery = true)
    void assignUserRoles(@Param("user_id")Long user_id,@Param("role_id")Long role_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles(role_name,created_at,updated_at,is_deleted,description) VALUES(:role_name,:created_at,:updated_at,:is_deleted,:description)",nativeQuery = true)
    void createRole(@Param("role_name")String role_name,@Param("created_at")Long created_at,@Param("updated_at")Long updated_at,@Param("is_deleted") Boolean is_deleted,@Param("description") String description);


    @Modifying
    @Transactional
    @Query(value = "UPDATE roles SET role_name = :role_name, updated_at = :updated_at, is_deleted = :is_deleted, description = :description WHERE id = :id", nativeQuery = true)
    void updateRole(@Param("id")Long id,@Param("role_name")String role_name,@Param("updated_at")Long updated_at,@Param("is_deleted") Boolean is_deleted,@Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles_resources_permissions(resources_permissions_id,role_id) VALUES(:resources_permissions_id,:role_id)",nativeQuery = true)
    void assignRolePermissions(@Param("resources_permissions_id")Long resources_permissions_id,@Param("role_id") Long role_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM roles_resources_permissions rrp WHERE rrp.role_id = :role_id",nativeQuery = true)
    void deleteAssignRolePermissions(@Param("role_id") Long role_id);
}

