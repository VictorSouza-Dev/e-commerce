package org.example.ecommerce.api.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("e-commerce API")
                        .version("1.0")
                        .description("API do sistema e-commerce")
                );
    }
}
