package moe.pine.spring.cache.interceptors;

public interface CacheHeader {
    String getName();

    String buildValue(CachePolicy cachePolicy);
}
