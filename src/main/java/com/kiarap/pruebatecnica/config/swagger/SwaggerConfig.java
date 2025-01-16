package com.kiarap.pruebatecnica.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "JWT";
    private static final String TITLE = "Prueba Técnica - EQUIPOS API";
    private static final String DESCRIPTION = "Proyecto Generado por Kiara Precopio";
    private static final String VERSION = "1.0";
    private static final Contact CONTACT = new Contact()
            .name("Kiara Precopio")
            .email("kprecopio@gmail.com");

    @Bean
    public OpenAPI initialOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, 
                            new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .contact(CONTACT));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("1. Authentication")
                .pathsToMatch("/auth/**")
                .addOpenApiCustomiser(openApi -> openApi
                        .info(new Info()
                                .title("Authentication API")
                                .description("Endpoints públicos para autenticación")
                                .version(VERSION)
                                .contact(CONTACT)))
                .build();
    }

    @Bean
    public GroupedOpenApi protectedApi() {
        return GroupedOpenApi.builder()
                .group("2. Equipos")
                .pathsToMatch("/equipos/**")
                .addOpenApiCustomiser(openApi -> openApi
                        .info(new Info()
                                .title("Equipos API")
                                .description("Endpoints protegidos para equipos")
                                .version(VERSION)
                                .contact(CONTACT))
                        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME)))
                .build();
    }
}