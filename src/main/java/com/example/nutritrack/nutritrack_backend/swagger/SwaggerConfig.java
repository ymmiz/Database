package com.example.nutritrack.nutritrack_backend.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Nutri_Track Backend API")
                .version("1.0")
                .description("API Documents for the Project")
                .contact(new Contact().name("Zin Me Me Thet").email("zinmemethet3181mdy@gmail.com")));
    }
}
