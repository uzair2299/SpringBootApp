package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.spi.LocaleNameProvider;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByIdIn(List<Long> ids);
}

