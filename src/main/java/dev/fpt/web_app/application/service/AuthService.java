package dev.fpt.web_app.application.service;

import dev.fpt.web_app.application.dto.auth.LoginRequest;
import dev.fpt.web_app.application.dto.auth.LoginResponse;
import dev.fpt.web_app.application.dto.auth.UserInfoResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest request);

    LoginResponse refresh(String refreshToken);

    void invalidate(String refreshToken);

    UserInfoResponse getCurrentUserInfo();
}
