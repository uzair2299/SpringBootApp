package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resource, Long> {
//    List<Role> findByIdIn(List<Long> ids);
//
//    List<Permission> findByIsDeletedFalse();
//
//    List<Permission> findByIsDeletedTrue();


    @Query(value = "SELECT r.id,r.resource_name,r.resource_endpoint, r.version,r.is_active, r.method_type FROM public.resources r\n",nativeQuery = true)
    List<Object[]> getAll();


    @Query(value = "SELECT r.id,r.resource_name,r.version, r.method_type, p.id,p.permission_name,rp.id as resources_Permissions_Id\n" +
            "FROM public.resources r\n" +
            "LEFT JOIN resources_permissions rp ON rp.resource_id =r.id\n" +
            "LEFT JOIN permission p ON rp.permission_id =p.id",nativeQuery = true)
    List<Object[]> getAllResourcesWithPermissions();

    @Query(value = "SELECT r.id,r.resource_name,r.version, r.method_type, p.id,p.permission_name\n" +
            "FROM public.resources r\n" +
            "LEFT JOIN resources_permissions rp ON rp.resource_id =r.id\n" +
            "LEFT JOIN permission p ON rp.permission_id =p.id",nativeQuery = true)
    List<Object[]> getResourceByIdWithPermissions(Long resource_id);

    //The @Transactional annotation is used to define the scope of a single database transaction. When applied to a method or a class, it specifies that the method or all methods in the class are transactional. This means that the methods will be executed within a transaction context, and they will either all succeed or all fail (rollback) as a unit.
    //The @Modifying annotation is used with a query method to indicate that the query is not a SELECT query and will modify the database (i.e., it's an INSERT, UPDATE, or DELETE operation). This annotation is necessary because Spring Data JPA's default behavior expects methods with @Query to be read-only unless explicitly stated otherwise.
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM resources_permissions r_p WHERE r_p.resource_id = :resource_id",nativeQuery = true)
    void deleteResourcePermissionById(Long resource_id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO resources_permissions(permission_id,resource_id) VALUES(:permission_id,:resource_id)",nativeQuery = true)
    void assignResourcePermission(Long permission_id,Long resource_id);



    @Modifying
    @Transactional
    @Query(value = "INSERT INTO resources(resource_name,resource_endpoint,version,method_type,is_active,is_auth_required,is_deprecated) VALUES(:resourceName,:resourceEndpoint,:version,:methodType,:active,:authRequired,:deprecated)",nativeQuery = true)
    void createResource(String resourceName, String resourceEndpoint, String version, String methodType, boolean active, boolean authRequired, boolean deprecated);

//    @Query(value = "SELECT * FROM public.permission where is_deleted = false\n" +
//            "ORDER BY id ASC ",nativeQuery = true)
//    List<Permission> getAllNonActive();
//
//    @Query(value = "SELECT * FROM public.permission where is_deleted = true\n" +
//            "ORDER BY id ASC ",nativeQuery = true)
//    List<Permission> getAllActive();
//
//    @Query(value = "SELECT * FROM public.permission ORDER BY id ASC ",nativeQuery = true)
//    List<Permission> getAll();
//
//    @Query(value = "SELECT * FROM get_active_permissions()", nativeQuery = true,name = "ActivePermissionMapping")
//    List<Permission> getActivePermissions();
//
//    @Query(value = "SELECT * FROM public.permission where is_deleted = false AND id=:id",nativeQuery = true)
//    Permission getActivePermissionById(@Param("id") Long id);
//
//    //@Query(value = "DELETE FROM public.permission where id=:id RETURNING 1",nativeQuery = true)
//    @Query(value = "DELETE FROM public.permission where id=:id",nativeQuery = true)
//    int hardDeletePermissionById(@Param("id") Long id);
}

