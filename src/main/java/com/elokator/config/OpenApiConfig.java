package com.elokator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenApiConfig() {
    }

    @Bean
    public OpenAPI apiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("eLokator - bezpieczny mieszkaniec")
                        .version("1.0.0")
                        .description("REST Api Dokumentacja"));
    }

    /*@Bean
    public GroupedOpenApi customerAccount() {
        return GroupedOpenApi.builder()
                .group("customer-account") // Nazwa grupy
                .pathsToMatch("/account/**") // Dopasowanie do ścieżek zaczynających się od /customer-account
                .build();
    }*/
}
