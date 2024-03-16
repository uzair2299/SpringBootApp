package com.example.SprintBootAppWithSQL.components;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;


/*
* Hibernate primarily focuses on object-relational mapping (ORM) and provides features for mapping Java objects
* to database tables and executing CRUD (Create, Read, Update, Delete) operations. However, managing the creation
* and deletion of stored procedures falls outside the scope of Hibernate's functionality.
*/

@Component
public class SqlScriptRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//
//    @Autowired
//    private DataSource dataSource;
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//        ClassPathResource scriptResource = new ClassPathResource("GetRolesSP.sql");
//        String scriptContent = StreamUtils.copyToString(scriptResource.getInputStream(), StandardCharsets.UTF_8);
//
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(scriptContent);
//        }
//    }
}




//@Configuration
//public class StartupConfig {
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public DataSourceInitializer dataSourceInitializer() {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("GetRolesSP.sql")));
//        return initializer;
//    }
//}