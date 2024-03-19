# ChâTop API
Backend API for the ChâTop project, providing essential functionalities to power the ChâTop application, a web application where people can rent and find rental properties to rent in the SouthWestern part of France.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed:
- JDK 21
- Maven 3.6 or newer
- MySQL Server 8.0.29 or newer

### Installing

1. **Clone the repository**

```bash
git clone https://github.com/Stephanefy/Developpez-le-back-end-en-utilisant-Java-et-Spring-sfy-backend.git
```

2. **Navigate to the prohject**
```
cd chatop-api
```

3. **Build the project**

Use Maven to build and package the application.

```
mvn clean install
```
4. **Configure the application (including Database configuration)**

Update the src/main/resources/application.properties file with your MySQL database credentials and any other configuration settings specific to your environment.

For example:
Configure your database connection settings in the application.properties or application.yml file in your src/main/resources directory. Specify details like the database URL, username, and password.
For MySQL configuration in application : 

        spring.datasource.url=jdbc:mysql://localhost:3306/{dbName}
        spring.datasource.username={dbUsername}
        spring.datasource.password={dbPassword}
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=create-drop



### Running the application

You can run the application using Maven

```
mvn spring-boot:run
```

The application should now be running and accessible. By default, Spring Boot applications run on port 8080.


### Accessing Swagger documentation

To access the OpenAPI documentation navigate to this link once you have built and run the project in development.

-> http://localhost:8080/api/swagger-ui/index.html

### Built with

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for creating stand-alone, production-grade Spring-based Applications
- [Spring Security](https://spring.io/projects/spring-security) - Authentication and access-control framework
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Simplifies data access for JPA-based repositories
- [Hibernate Validator](https://hibernate.org/validator/) - Reference implementation of Jakarta Bean Validation
- [Lombok](https://projectlombok.org/) - Java library that automatically plugs into your editor and build tools, spicing up your java
- [JJWT](https://github.com/jwtk/jjwt) - JSON Web Tokens for Java
- [ModelMapper](http://modelmapper.org/) - Object mapping library that makes it easy to convert one object type into another
- [SpringDoc OpenAPI](https://springdoc.org/) - Library for OpenAPI 3 with spring boot
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) - Official JDBC driver for MySQL databases

### License

This project is licensed under the MIT License - see the LICENSE file for details.

