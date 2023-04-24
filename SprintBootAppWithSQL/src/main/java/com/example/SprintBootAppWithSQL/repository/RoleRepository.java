package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}

