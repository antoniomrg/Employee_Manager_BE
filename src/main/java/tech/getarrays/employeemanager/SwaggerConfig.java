package tech.getarrays.employeemanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =  @Info(
                contact = @Contact(
                        name = "Antonio Morgano",
                        email = "antonio.morgano@aitho.it"
                ),
                description = "OpenAPI documentation for Employee Manager application",
                title = "Dunder Mifflin Employee Manager API documentation",
                version = "1.0",
                termsOfService = "Terms of Service"
        )
)
public class SwaggerConfig {
}
