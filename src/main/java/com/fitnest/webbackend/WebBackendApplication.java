package com.fitnest.webbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import com.fitnest.webbackend.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "FitNest Backend API",
                version = "1.0",
                description = "FitNest layihəsi üçün qurulmuş Uİ"
        ))
@EnableConfigurationProperties(JwtProperties.class)
public class WebBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBackendApplication.class, args);
    }
}
