package com.example.backend.config;


import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.example.backend.constant.Utils.APP_ROOT;


public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Gestion de gym")
                                .title("Gestion de GYM API")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.backend"))
                .paths(PathSelectors.ant(APP_ROOT+ "/**"))
                .build();
    }
}

