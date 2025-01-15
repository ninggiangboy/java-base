package dev.ngb.issues_logging_app.infrastructure.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.ngb.issues_logging_app.infrastructure.config.property.TokenExpirationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private final TokenExpirationProperties tokenExpirationProperties;

    public CacheConfig(TokenExpirationProperties tokenExpirationProperties) {
        this.tokenExpirationProperties = tokenExpirationProperties;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        Cache<Object, Object> dailyCacheOptions = Caffeine.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build();
        caffeineCacheManager.registerCustomCache(CacheConstant.USERS_CACHE_NAME, dailyCacheOptions);

        Cache<Object, Object> cacheRefreshToken = Caffeine.newBuilder()
                .expireAfterWrite(tokenExpirationProperties.refresh(), TimeUnit.MILLISECONDS)
                .build();
        caffeineCacheManager.registerCustomCache(CacheConstant.REFRESH_TOKEN_CACHE_NAME, cacheRefreshToken);

        Cache<Object, Object> cacheVerificationToken = Caffeine.newBuilder()
                .expireAfterWrite(tokenExpirationProperties.verification(), TimeUnit.MILLISECONDS)
                .build();
        caffeineCacheManager.registerCustomCache(CacheConstant.VERIFICATION_TOKEN_CACHE_NAME, cacheVerificationToken);
        return caffeineCacheManager;
    }
}
