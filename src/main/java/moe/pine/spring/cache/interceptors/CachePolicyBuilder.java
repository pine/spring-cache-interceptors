package moe.pine.spring.cache.interceptors;

import org.springframework.lang.Nullable;

public class CachePolicyBuilder {
    private boolean public_;
    private boolean private_;
    private boolean noCache;
    private boolean onlyIfCached;

    @Nullable
    private Long maxAge;

    @Nullable
    private Long sMaxAge;

    @Nullable
    private CachePolicy.MaxStale maxStale;

    @Nullable
    private Long minFresh;

    @Nullable
    private Long staleWhileRevalidate;

    @Nullable
    private Long staleIfError;

    private boolean mustRevalidate;
    private boolean proxyRevalidate;
    private boolean immutable;
    private boolean noStore;
    private boolean noTransform;

    public CachePolicyBuilder public_() {
        public_ = true;
        return this;
    }

    public CachePolicyBuilder private_() {
        private_ = true;
        return this;
    }

    public CachePolicyBuilder noCache() {
        noCache = true;
        return this;
    }

    public CachePolicyBuilder onlyIfCached() {
        onlyIfCached = true;
        return this;
    }

    public CachePolicyBuilder maxAge(long maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public CachePolicyBuilder sMaxAge(long sMaxAge) {
        this.sMaxAge = sMaxAge;
        return this;
    }

    public CachePolicyBuilder maxStale(@Nullable CachePolicy.MaxStale maxStale) {
        this.maxStale = maxStale;
        return this;
    }

    public CachePolicyBuilder minFresh(long seconds) {
        minFresh = seconds;
        return this;
    }

    public CachePolicyBuilder staleWhileRevalidate(long seconds) {
        staleWhileRevalidate = seconds;
        return this;
    }

    public CachePolicyBuilder staleIfError(long seconds) {
        staleIfError = seconds;
        return this;
    }

    public CachePolicyBuilder mustRevalidate() {
        mustRevalidate = true;
        return this;
    }

    public CachePolicyBuilder proxyRevalidate() {
        proxyRevalidate = true;
        return this;
    }

    public CachePolicyBuilder immutable() {
        immutable = true;
        return this;
    }

    public CachePolicyBuilder noStore() {
        noStore = true;
        return this;
    }

    public CachePolicyBuilder noTransform() {
        noTransform = true;
        return this;
    }

    public CachePolicy build() {
        return new CachePolicy(
                public_,
                private_,
                noCache,
                onlyIfCached,
                maxAge,
                sMaxAge,
                maxStale,
                minFresh,
                staleWhileRevalidate,
                staleIfError,
                mustRevalidate,
                proxyRevalidate,
                immutable,
                noStore,
                noTransform
        );
    }
}
