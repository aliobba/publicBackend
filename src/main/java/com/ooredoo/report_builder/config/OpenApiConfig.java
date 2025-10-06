package com.ooredoo.report_builder.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
    info = @Info(
        title = "OpenApi specification for ooredoo Project",
        version = "0.1",
        description = "OpenAPI documentation for security",
        contact = @Contact(name = "iheb Ayari", email = "iheb.ayari@esprit.tn"),
        license = @License(name = "ooredoo license")
    ),
    security = @SecurityRequirement(name = "bearerAuth")
)
public class OpenApiConfig {}
