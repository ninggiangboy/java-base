package dev.fpt.web_app.infrastructure.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CacheFactory {

    private final Map<String, CacheRepository> cacheRepositories;

    public CacheFactory(List<CacheManager> cacheManagers) {
        this.cacheRepositories = cacheManagers.stream()
                .flatMap(cacheManager -> cacheManager.getCacheNames()
                        .stream()
                        .map(name -> {
                            Cache cache = cacheManager.getCache(name);
                            return cache != null ? Map.entry(name, new DefaultCacheRepository(cache)) : null;
                        }))
                .filter(Objects::nonNull) // Ensure no null entries
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("Initialized CacheFactory with caches: {}", cacheRepositories.keySet());
    }

    public CacheRepository getCacheRepository(String cacheName) {
        CacheRepository service = cacheRepositories.get(cacheName);
        if (service == null) {
            throw new IllegalArgumentException("CacheService for cache '" + cacheName + "' not found");
        }
        return service;
    }
}
