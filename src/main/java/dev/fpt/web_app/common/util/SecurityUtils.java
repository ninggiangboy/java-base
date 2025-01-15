package dev.fpt.web_app.common.util;

import dev.fpt.web_app.domain.entity.User;
import dev.fpt.web_app.infrastructure.security.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class SecurityUtils {

    public static boolean isCurrentUserAdmin() {
        String adminRole = AuthConstant.ROLE_PREFIX + AuthConstant.ADMIN_ROLE_NAME;
        return isCurrentHasRole(adminRole);
    }

    public static User getCurrentUser() {
        return User.builder().id(getCurrentUserId()).build();
    }

    public static boolean isCurrentHasRole(String role) {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getAuthorities)
                .map(grantedAuthorities -> grantedAuthorities.stream()
                        .anyMatch(grantedAuthority -> grantedAuthority
                                .getAuthority().equals(role)))
                .orElse(false);
    }

    public static UUID getCurrentUserId() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> switch (principal) {
                    case UserDetails userDetails -> userDetails.getUsername();
                    case Jwt jwt -> jwt.getSubject();
                    default -> principal.toString();
                })
                .map(UUID::fromString)
                .orElse(null);
    }

    public static Collection<GrantedAuthority> getAdminAuthorities() {
        return List.of(new SimpleGrantedAuthority(AuthConstant.ROLE_PREFIX + AuthConstant.ADMIN_ROLE_NAME));
    }
}
