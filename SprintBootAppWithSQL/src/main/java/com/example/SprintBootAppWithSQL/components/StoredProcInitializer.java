package com.example.SprintBootAppWithSQL.components;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
* StoredProcInitializer component is responsible for executing the SQL script file during application startup.
* The script file path is specified as SQLScripts/storeProcedure/GetRolesSP.sql
*/
@Component
public class StoredProcInitializer {
    private final SessionFactory sessionFactory;


    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    public StoredProcInitializer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @PostConstruct
    public void initializeStoredProc() {
       // try {
            // Load the SQL script file
            //Path filePath = Paths.get("/SQLScripts/storeProcedure/GetRolesSP.sql");
            //String script = Files.readString(filePath);
//            Resource resource = resourceLoader.getResource("classpath:SQLScripts/storeProcedure/GetRolesSP.sql");
//            File file = resource.getFile();
//            String script = Files.readString(Path.of(file.getPath().toString()));
//            // Execute the script
//            Session session = sessionFactory.openSession();
//            session.createQuery(script).executeUpdate();
//            session.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
