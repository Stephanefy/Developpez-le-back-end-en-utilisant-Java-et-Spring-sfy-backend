package com.chatop.chatopapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Chatôp API documentation",
				description = "This is the documentation for calling Chatôp API, a web application that helps house owners rent their property to potential tenants efficiently and securely, by providing a platform for listing properties, scheduling viewings, and managing rental agreements.",
				version = "v1.0",
				contact = @Contact(
						name = "Stéphane",
						email = "stephanefokyin@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://swagger.io/docs/"
		)
)
public class ChatopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatopApiApplication.class, args);
	}

}
