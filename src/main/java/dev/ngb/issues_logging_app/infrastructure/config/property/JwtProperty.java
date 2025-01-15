package dev.ngb.issues_logging_app.infrastructure.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "token.jwt")
public record JwtProperty(
        String secretKey,
        long expirationDuration
) {
}
