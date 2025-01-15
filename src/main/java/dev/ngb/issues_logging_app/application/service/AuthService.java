package dev.ngb.issues_logging_app.application.service;

import dev.ngb.issues_logging_app.application.dto.auth.LoginRequest;
import dev.ngb.issues_logging_app.application.dto.auth.LoginResponse;
import dev.ngb.issues_logging_app.application.dto.auth.UserInfoResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest request);

    LoginResponse refresh(String refreshToken);

    void invalidate(String refreshToken);

    UserInfoResponse getCurrentUserInfo();
}
