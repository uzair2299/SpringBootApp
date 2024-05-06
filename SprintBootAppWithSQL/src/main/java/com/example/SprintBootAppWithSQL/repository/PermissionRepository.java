package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Role> findByIdIn(List<Long> ids);

    List<Permission> findByIsDeletedFalse();

    List<Permission> findByIsDeletedTrue();

    @Query(value = "SELECT * FROM public.permissions where is_deleted = false\n" +
            "ORDER BY id ASC ",nativeQuery = true)
    List<Object[]> getAll();
}

