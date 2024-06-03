package com.example.SprintBootAppWithSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
@EnableCaching
@SpringBootApplication
public class SprintBootAppWithSqlApplication {

	public static void main(String[] args) {

	ApplicationContext context =  SpringApplication.run(SprintBootAppWithSqlApplication.class, args);
	}
}


