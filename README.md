This project is a simple example of a CRUD (Create, Read, Update, Delete) operation using Spring Boot, Spring Data JPA, and SQL Server. The project is built using Maven.

<h1>Getting Started</h1>

To run this project, you need to have the following prerequisites installed on your system:

1. JDK 1.8 or later
2. Maven 3 or later
3. Microsoft SQL Server

<h1>Database Setup</h1>
1. Create a new database in SQL Server.
2. Execute the create-tables.sql script located in the src/main/resources directory to create the necessary tables.
3. Update the application.properties file located in the src/main/resources directory with your SQL Server connection details:


```
spring.datasource.url= jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=db_name
spring.datasource.username= sa
spring.datasource.password= 123456
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto= update
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
```

<h1>API Endpoints</h1>

The following endpoints are available for this application:

<h2>Create a new employee</h2>
/api/v1/users

Example request body:

```
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
}
```



