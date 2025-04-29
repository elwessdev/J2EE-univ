package com.example.bookcatalog.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookCatalogOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Catalog API")
                        .description("Spring Boot REST API for managing a book catalog")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Your Name")
                                .url("https://github.com/yourusername")
                                .email("your.email@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Book Catalog API Documentation")
                        .url("https://github.com/yourusername/bookcatalog"));
    }
}