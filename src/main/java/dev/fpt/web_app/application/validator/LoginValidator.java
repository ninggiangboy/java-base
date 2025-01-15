package dev.fpt.web_app.application.validator;

import dev.fpt.web_app.application.dto.auth.LoginRequest;
import dev.fpt.web_app.common.util.StringUtils;
import dev.fpt.web_app.common.validator.ValidatorChain;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator extends ValidatorChain<LoginRequest> {
    public LoginValidator() {
        ruleFor(LoginRequest.Fields.email, LoginRequest::email)
                .require(StringUtils::isNotBlank, "Email is required")
                .require(StringUtils::isEmail, "Email is invalid");
        ruleFor(LoginRequest.Fields.password, LoginRequest::password)
                .require(StringUtils::isNotBlank, "Password is required");
    }
}
