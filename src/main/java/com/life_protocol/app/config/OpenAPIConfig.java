package com.life_protocol.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI lifeProtocolOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Life Protocol API")
                        .description("API for Life Protocol application")
                        .version("1.0")
                        .contact(new Contact().name("Your Name").url("Your Website").email("Your Email"))
                        .license(new License().name("License of API").url("License URL")));
    }
}
