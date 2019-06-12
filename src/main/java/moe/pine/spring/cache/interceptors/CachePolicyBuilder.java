package moe.pine.spring.cache.interceptors;

public class CachePolicyBuilder {
    private final CachePolicy cachePolicy = new CachePolicy();

    public CachePolicyBuilder public_() {
        cachePolicy.setPublic(true);
        return this;
    }

    public CachePolicyBuilder private_() {
        cachePolicy.setPrivate(true);
        return this;
    }

    public CachePolicyBuilder noCache() {
        cachePolicy.setNoCache(true);
        return this;
    }

    public CachePolicyBuilder maxAge(long maxAge) {
        cachePolicy.setMaxAge(maxAge);
        return this;
    }

    public CachePolicyBuilder noStore() {
        cachePolicy.setNoStore(true);
        return this;
    }

    public CachePolicyBuilder mustRevalidate() {
        cachePolicy.setMustRevalidate(true);
        return this;
    }

    public CachePolicy build() {
        return cachePolicy;
    }
}
