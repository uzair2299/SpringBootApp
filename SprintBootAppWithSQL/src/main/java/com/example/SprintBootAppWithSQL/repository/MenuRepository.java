package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.dto.MenuDto;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "\n" +
            "WITH RECURSIVE MenuHierarchy AS (\n" +
            "    SELECT id, link, name,parent_id ,0 AS level\n" +
            "    FROM public.menu\n" +
            "    WHERE parent_id IS NULL\n" +
            "\n" +
            "    UNION ALL\n" +
            "\n" +
            "    SELECT e.id, e.link,e.name,e.parent_id, eh.level + 1\n" +
            "    FROM public.menu e\n" +
            "    JOIN  MenuHierarchy eh ON e.parent_id = eh.id\n" +
            ")\n" +
            "SELECT id, name, level,parent_id,link\n" +
            "FROM MenuHierarchy", nativeQuery = true)
    List<Object[]> getMenuHierarchy();
}

