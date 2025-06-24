package com.EventBookingSystem.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventBookingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Booking System API")
                        .description("Backend REST APIs for user and event management")
                        .version("1.0.0")
                );
    }
}
