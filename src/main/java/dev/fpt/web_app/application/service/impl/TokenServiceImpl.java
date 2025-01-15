package dev.fpt.web_app.application.service.impl;

import dev.fpt.web_app.application.constant.TokenType;
import dev.fpt.web_app.application.service.TokenService;
import dev.fpt.web_app.infrastructure.cache.CacheFactory;
import dev.fpt.web_app.infrastructure.cache.CacheRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final CacheFactory cacheFactory;

    public TokenServiceImpl(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    @Override
    public String generateAndSaveUserToken(String userId, TokenType type) {
        String token = generateTokenValue();
        saveToken(userId, token, type);
        return token;
    }

    @Override
    public UUID getUserIdByToken(String token, TokenType type) {
        String key = getKeyCache(token, type);
        CacheRepository cache = getTokenCache(type);
        String userId = cache.findByKey(key, String.class)
                .orElseThrow(() -> new IllegalArgumentException("Token is invalid"));
        return UUID.fromString(userId);
    }

    @Override
    public void revokeToken(String token, TokenType type) {
        String key = getKeyCache(token, type);
        CacheRepository cache = getTokenCache(type);
        cache.delete(key);
    }

    private String getKeyCache(String token, TokenType type) {
        return type.getCacheKeyPattern().formatted(token);
    }

    private void saveToken(String userId, String token, TokenType type) {
        String key = getKeyCache(token, type);
        CacheRepository cache = getTokenCache(type);
        cache.save(key, userId);
    }

    private String generateTokenValue() {
        return UUID.randomUUID().toString();
    }

    private CacheRepository getTokenCache(TokenType type) {
        return cacheFactory.getCacheRepository(type.getCacheName());
    }
}
