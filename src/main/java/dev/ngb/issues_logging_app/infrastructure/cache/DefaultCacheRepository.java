package dev.ngb.issues_logging_app.infrastructure.cache;

import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.function.Supplier;

public class DefaultCacheRepository implements CacheRepository {

    private final Cache cache;

    public DefaultCacheRepository(Cache cache) {
        this.cache = cache;
    }

    @Override
    public String getCacheName() {
        return cache.getName();
    }

    @Override
    public Optional<?> findByKey(Object key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public <T> Optional<T> findByKey(Object key, Class<T> type) {
        return Optional.ofNullable(cache.get(key, type));
    }

    @Override
    public <T> T findByKeyOrSave(Object key, Class<T> type, Supplier<T> getValueSupplier) {
        return Optional.ofNullable(cache.get(key, type))
                .orElseGet(() -> {
                    T value = getValueSupplier.get();
                    cache.put(key, value);
                    return value;
                });
    }

    @Override
    public void save(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void delete(Object key) {
        cache.evict(key);
    }
}
