package dev.fpt.web_app.infrastructure.cache;

import java.util.Optional;
import java.util.function.Supplier;

public interface CacheRepository {
    String getCacheName();

    Optional<?> findByKey(Object key);

    <T> Optional<T> findByKey(Object key, Class<T> type);

    <T> T findByKeyOrSave(Object key, Class<T> type, Supplier<T> getValueSupplier);

    void save(Object key, Object value);

    void delete(Object key);
}
