package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.entities.Role;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Role> findByIdIn(List<Long> ids);

    List<Permission> findByIsDeletedFalse();

    List<Permission> findByIsDeletedTrue();

    @Query(value = "SELECT * FROM public.permission where is_deleted = false\n" +
            "ORDER BY id ASC ",nativeQuery = true)
    List<Object[]> getAll_();


    @Query(value = "SELECT * FROM public.permission where is_deleted = false\n" +
            "ORDER BY id ASC ",nativeQuery = true)
    List<Permission> getAllNonActive();

    @Query(value = "SELECT * FROM public.permission where is_deleted = true\n" +
            "ORDER BY id ASC ",nativeQuery = true)
    List<Permission> getAllActive();

    @Query(value = "SELECT * FROM public.permission ORDER BY id ASC ",nativeQuery = true)
    List<Permission> getAll();

    @Query(value = "SELECT * FROM get_active_permissions()", nativeQuery = true,name = "ActivePermissionMapping")
    List<Permission> getActivePermissions();

    @Query(value = "SELECT * FROM public.permission where is_deleted = false AND id=:id",nativeQuery = true)
    Permission getActivePermissionById(@Param("id") Long id);

    //@Query(value = "DELETE FROM public.permission where id=:id RETURNING 1",nativeQuery = true)
    @Query(value = "DELETE FROM public.permission where id=:id",nativeQuery = true)
    void hardDeletePermissionById(@Param("id") Long id);
}

