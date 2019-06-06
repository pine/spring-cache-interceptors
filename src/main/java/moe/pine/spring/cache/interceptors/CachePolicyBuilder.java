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

    public CachePolicyBuilder noStore() {
        return this;
    }

    public CachePolicy build() {
        return cachePolicy;
    }
}
