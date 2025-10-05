package com.ooredoo.report_builder.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "iheb Ayari",
                        email = "iheb.ayari@esprit.tn"
                ),
                description = "OpenAPI documentation for security",
                title = "OpenApi specification for ooredoo Project",
                version = "1.0",
                license = @License(
                        name = "ooredoo license"
                )

        ),
        servers = {
                @Server(
                        description = "local dev env",
                        url = "http://localhost:8080/api/v1/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
/*
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                clientCredentials =
                @OAuthFlow(
                        authorizationUrl = "http://localhost:9090/realms/book-social-network/protocol/openid-connect/auth"
                )
        ),
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)*/
public class OpenApiConfig {
}
