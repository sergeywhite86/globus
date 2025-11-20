package org.sergey_white.globus.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Учебный проект ", version = "1.0", description = """
                Приложение для CRUD операций c User
                """)

)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI();

    }
}
