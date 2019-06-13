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

    public CachePolicyBuilder onlyIfCached() {
        cachePolicy.setOnlyIfCached(true);
        return this;
    }

    public CachePolicyBuilder maxAge(long maxAge) {
        cachePolicy.setMaxAge(maxAge);
        return this;
    }

    public CachePolicyBuilder sMaxAge(long sMaxAge) {
        cachePolicy.setSMaxAge(sMaxAge);
        return this;
    }

    public CachePolicyBuilder maxStale(CachePolicy.MaxStale maxStale) {
        cachePolicy.setMaxStale(maxStale);
        return this;
    }

    public CachePolicyBuilder minFresh(long seconds) {
        cachePolicy.setMinFresh(seconds);
        return this;
    }

    public CachePolicyBuilder staleIfError(long seconds) {
        cachePolicy.setStaleIfError(seconds);
        return this;
    }

    public CachePolicyBuilder mustRevalidate() {
        cachePolicy.setMustRevalidate(true);
        return this;
    }

    public CachePolicyBuilder proxyRevalidate() {
        cachePolicy.setProxyRevalidate(true);
        return this;
    }

    public CachePolicyBuilder immutable() {
        cachePolicy.setImmutable(true);
        return this;
    }

    public CachePolicyBuilder noStore() {
        cachePolicy.setNoStore(true);
        return this;
    }

    public CachePolicyBuilder noTransform() {
        cachePolicy.setNoTransform(true);
        return this;
    }

    public CachePolicy build() {
        return cachePolicy;
    }
}
