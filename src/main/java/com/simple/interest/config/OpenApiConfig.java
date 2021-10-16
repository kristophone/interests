package com.simple.interest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // https://github.com/swagger-api/swagger-ui#localization-and-translation
    // i10n (translations) is not implemented.

    @Bean
    public OpenAPI customOpenAPI() {

        Server serverLocal = new Server();
        serverLocal.setUrl("http://localhost:8080/");
        serverLocal.setDescription("Default");

        return new OpenAPI()
                .info(new Info().title("Documentación API").description(
                        "Documentación Spring Boot 2 REST full y OpenAPI 3."))
                .addServersItem(serverLocal);
    }

}