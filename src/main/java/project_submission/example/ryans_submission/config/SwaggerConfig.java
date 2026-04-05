package project_submission.example.ryans_submission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI financeDashboardAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Financial Dashboard API")
                .version("1.0")
                .description("Endpoints for managing transactions, accounts, and reports"));
    }
}
