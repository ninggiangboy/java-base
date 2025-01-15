package dev.ngb.issues_logging_app.infrastructure.config.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperty.class, TokenExpirationProperties.class, StorageProperties.class})
public class PropertyConfig {
}
