package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.Categories;
import com.example.SprintBootAppWithSQL.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
//
//    @Query(value = "\n" +
//            "WITH RECURSIVE CategoryHierarchy AS (\n" +
//            "    SELECT id, name,parent_id,0 AS level\n" +
//            "    FROM public.Categories\n" +
//            "    WHERE parent_id IS NULL\n" +
//            "\n" +
//            "    UNION ALL\n" +
//            "\n" +
//            "    SELECT e.id,e.name,e.parent_id, eh.level + 1\n" +
//            "    FROM public.Categories e\n" +
//            "    JOIN  CategoryHierarchy eh ON e.parent_id = eh.id\n" +
//            ")\n" +
//            "SELECT id, name, level,parent_id\n" +
//            "FROM CategoryHierarchy", nativeQuery = true)
//    List<Object[]> getCategoryHierarchy();


    //
    @Query(value = "SELECT id, name, COALESCE(parent_id, 0) FROM Categories", nativeQuery = true)
    List<Object[]> getCategoryHierarchy();
}

