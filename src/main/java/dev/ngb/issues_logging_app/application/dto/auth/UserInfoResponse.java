package dev.ngb.issues_logging_app.application.dto.auth;

import java.util.UUID;

public record UserInfoResponse(
        UUID id,
        String username,
        String fullName,
        String email
) {
}
