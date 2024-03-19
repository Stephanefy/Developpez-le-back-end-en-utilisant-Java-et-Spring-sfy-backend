package com.chatop.chatopapi.configuration;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI customizeOpenAPI() {

        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description(
                                        "Provide the JWT token. A JWT token can be obtained from the Authentication API. For testing, register a user like &nbsp;  <strong></br>email: johndoe@test.com</br> username: john</br> password: john</strong> </br> a token will be returned, use this token to be authorized to test the API on Swagger.")
                                .bearerFormat("JWT")));

    }
}
