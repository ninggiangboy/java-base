package dev.fpt.web_app.api.endpoint;

import dev.fpt.web_app.application.dto.auth.LoginRequest;
import dev.fpt.web_app.application.dto.auth.LoginResponse;
import dev.fpt.web_app.application.dto.auth.UserInfoResponse;
import dev.fpt.web_app.common.model.ApiResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
public interface AuthEndpoint {
    @PostMapping("/login")
    ApiResult<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PostMapping("/refresh")
    ApiResult<LoginResponse> refresh(@RequestParam("token") String refreshToken);

    @GetMapping("/whoami")
    ApiResult<UserInfoResponse> whoami();

    @DeleteMapping("/logout")
    ApiResult<Void> logout(@RequestParam("token") String refreshToken);
}
