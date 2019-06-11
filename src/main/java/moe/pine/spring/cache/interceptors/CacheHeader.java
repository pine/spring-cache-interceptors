package moe.pine.spring.cache.interceptors;

import java.time.Clock;

public interface CacheHeader {
    String getName();

    String buildValue(CachePolicy cachePolicy, Clock clock);
}
