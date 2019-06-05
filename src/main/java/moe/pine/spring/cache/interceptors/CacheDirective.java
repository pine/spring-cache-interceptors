package moe.pine.spring.cache.interceptors;

public interface CacheDirective {
    String compile(CacheHeader header);
}
