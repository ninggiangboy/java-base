package dev.fpt.web_app.application.dto.auth;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserInfoResponse(
        UUID id,
        String username,
        String fullName,
        String email
) {
}
