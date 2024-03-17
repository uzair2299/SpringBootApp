package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.RolesMenu;
import com.example.SprintBootAppWithSQL.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesMenuRepository extends JpaRepository<RolesMenu,Long> {

}

