package dev.fpt.web_app.infrastructure.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public record StorageProperties(
        String location
) {
}
