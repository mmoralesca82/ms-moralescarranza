package com.codigo.msmoralescarranza.application.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI()
                .info(new Info().title("Apis del MS-MORALESCARRANZA").version("2.0"));
    }
}
