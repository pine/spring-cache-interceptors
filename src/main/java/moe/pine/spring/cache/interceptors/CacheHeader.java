package moe.pine.spring.cache.interceptors;

import java.time.Clock;
import java.util.Optional;

public interface CacheHeader {
    String getName();

    Optional<String> buildValue(CachePolicy cachePolicy, Clock clock);
}
