package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Role> findByIdIn(List<Long> ids);
}

