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

2. **Navigate to the project**
```
cd Developpez-le-back-end-en-utilisant-Java-et-Spring-sfy-backend
```
3. **Set environment variables**

Whether you are using Eclipse or IntelliJ, edit your IDE configuration and set environment variables with these keys and run the application through the IDE.
```
DB_NAME=<your-database-name>
DB_USERNAME=<your-database-admin-username>
DB_PASSWORD=<your-database-password>
JWT_SECRET=<base64-key>
```
tips: you can generate a random base64 key secured enought with this command :
```
openssl rand -base64 32
```

```
mvn clean install
```
4. **Configure the application (including Database configuration)**

Normally by setting the environment variables in your IDE and running through it you won't need to manually add credentials.
    


### Running the application through your IDE


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

