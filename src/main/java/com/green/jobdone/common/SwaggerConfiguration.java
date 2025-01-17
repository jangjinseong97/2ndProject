package com.green.jobdone.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
    info = @Info(
          title = "GreenGram"
        , description = "그린그램 SNS"
        , version = "v2"
    )
    , security = @SecurityRequirement(name = "Authorization")
)

@SecurityScheme(
          type = SecuritySchemeType.HTTP
        , name = "Authorization"
        , in = SecuritySchemeIn.HEADER
        , bearerFormat = "JWT"
        , scheme = "Bearer"
)
public class SwaggerConfiguration { }
